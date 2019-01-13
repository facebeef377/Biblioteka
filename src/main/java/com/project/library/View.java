package com.project.library;

public class View {


    public interface SimpleAuthor {
    }
    public interface SimpleUser {
    }
    public interface SimpleLease extends SimpleBook,SimpleUser {
    }

    public interface SimpleAuthorWithBook extends SimpleAuthor,SimpleBook{
    }

    public interface SimpleBook {
    }
    public interface BookWithAuthor extends SimpleBook ,SimpleAuthor{
    }
}
