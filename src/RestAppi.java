import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

public class RestAppi {
    public RestAppi(){
    }
    public void getTodos(){
        try {
            trygetTodos();
            
        } catch (IOException e) {
            System.err.println("Hiba!");
        }
    }
    public void trygetTodos() throws IOException{
        URL url = new URL("https://jsonplaceholder.typicode.com/todos");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.connect();
        
        String text = "";
        int responseCode = http.getResponseCode();
        if(responseCode == 200){
            text = new String(http.getInputStream().readAllBytes(),StandardCharsets.UTF_8);
        }
       // System.out.println(text);

        Gson gson = new Gson();
        Todo[] todoArray = gson.fromJson(text, Todo[].class);
        ArrayList<Todo> todoList = new ArrayList<>(Arrays.asList(todoArray));
        System.out.println(todoList.get(0).title);
    }
}
