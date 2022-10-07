
import java.io.IOException;
import java.net.URI;
import java.util.*;
class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    ArrayList<String> items = new ArrayList<String>();
    public String handleRequest(URI url) {
        if (url.getPath().equals("/add")) {
           String[] parameters = url.getQuery().split("=");
           if(parameters[0].equals("s")){
                items.add(parameters[1]);
           }
           return "ITEM ADDED WOOOOOOOOO";
        } else if(url.getPath().contains("/search")){
            String[] parameters = url.getQuery().split("=");
            System.out.println(Arrays.toString(parameters));
           if(parameters[0].equals("s")){
                //SEARCH CODE HERE
                String ot = "";
                for(int i = 0; i < items.size(); i++){
                    if(items.get(i).contains(parameters[1])){
                        ot += items.get(i) + "\n";
                    }
                }
                return ot;
           }
        }
        return "404 not found";
        
    }
}
class SearchEngine{
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
