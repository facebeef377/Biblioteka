package com.project.library;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping(path = "/lease")
public class LeaseController {


    @Autowired
    LeaseRepository leaseRepository;
    @Autowired
    BookRepository bookRepository;

    @PostMapping(path = "/all")
    @JsonView(View.SimpleLease.class)
    public @ResponseBody
    Iterable<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    Map<String, String> addLease(@RequestBody Lease lease) {

        List<Lease> leases=leaseRepository.getByBookNotReturned(lease.getBook().getId());
        if(leases.size()+1>bookRepository.getById(lease.getBook().getId()).getQuantity())
        {
            return ApiResponse.responseFailed();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 14); 
        Timestamp timestamp = new Timestamp(c.getTimeInMillis());
        lease.setExpiration_date(timestamp);
        bookRepository.getById(lease.getBook().getId()).borrow();
        leaseRepository.save(lease);
        return ApiResponse.responseOK();
    }

    @PostMapping(path = "/return")
    public @ResponseBody
    Map<String, String> returnBook(@RequestBody Lease lease_id) {

        Lease lease=leaseRepository.findOne(lease_id.getId());
        if(lease.getReturn_date()==null)
        {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        lease.setReturn_date(timestamp);
        lease.getBook().giveBack();
        leaseRepository.save(lease);
            return ApiResponse.responseOK();
        }
        return ApiResponse.responseFailed();
    }

    @PostMapping(path = "/delete")
    public @ResponseBody
    Map<String, String> removeLease(@RequestBody Lease lease) {

        leaseRepository.delete(lease.getId());
        return ApiResponse.responseOK();
    }

}
