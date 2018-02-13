public class UserInterface {

    public String action(String command){
        return help();
    }

    public String go() {
        return "GO";
    }

    public String use() {
        return "USE";
    }

    public String take() {
        return "TAKE";
    }

    public String search() {
        return "SEARCH";
    }

    public String open() {
        return "OPEN";
    }

    public String help(){
        return "Usable commands:\nGO\nUSE\nTAKE\nSEARCH\nOPEN\nINVENTORY\nHELP";
    }

    public String actionTarget() {
        return null;
    }

}
