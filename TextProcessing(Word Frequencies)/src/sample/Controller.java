package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;


public class Controller
{
    @FXML
    private Pane pnl;

    @FXML
    private JFXTextField txtDirectory;

    @FXML
    private JFXButton btnCreate;

    @FXML
    private JFXButton btnImport;

    @FXML
    private JFXTextField txtWord;

    @FXML
    private JFXButton btnCount;


    File fl;



    @FXML
    void makeImport(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("txt files  *.txt","*.txt"));
        File selectedFile = fc.showOpenDialog(null);
        fl = selectedFile;


        if (selectedFile != null)
        {
            txtDirectory.setText(selectedFile.getPath());
        }
        else
        {
            System.out.println("No file was chosen");
        }




    }

    @FXML
    void makeCount(ActionEvent event)
    {
        wordCount(fl);

    }

    @FXML
    void makeCreate(ActionEvent event)
    {
        String s = txtWord.getText();
        String token = "";
        List<String> temps = new ArrayList<>();
        try
        {
            Scanner scan = new Scanner(fl).useDelimiter(",\\s*");
            while (scan.hasNext())
            {
                token = scan.next();
                String[] word = token.split("\\W");
                for (int i = 0; i< word.length;i++)
                {
                    if(!word[i].isEmpty()){
                        temps.add(word[i].toLowerCase());}
                }

            }
            scan.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        String[] allTxt = temps.toArray(new String[0]);


        newSentence(allTxt,s);




    }

    void wordCount(File f)
    {

        HashMap<String,Integer> counter = new HashMap<>();


        String token = "";
        List<String> temps = new ArrayList<>();
        try
        {
            Scanner sc = new Scanner(f).useDelimiter(",\\s*");
            while (sc.hasNext())
            {
                token = sc.next();
                String[] word = token.split("\\W");
                for (int i = 0; i< word.length;i++)
                {
                    if(!word[i].isEmpty()){
                    temps.add(word[i].toLowerCase());}
                }

            }
            sc.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        String[] words = temps.toArray(new String[0]);

        for (int i = 0; i < words.length ; i++)
        {
            String let = words[i];
            if(counter.containsKey(let))
            {
                int increment = counter.get(words[i]) + 1;
                counter.put(let,increment);


            }
            else
            {
                counter.put(let,1);

            }


        }



       List<Map.Entry<String,Integer>> list = new LinkedList<>(counter.entrySet());
       Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
           @Override
           public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
               return o2.getValue().compareTo(o1.getValue());
           }
       });


        for (Map.Entry<String,Integer> t: list)
        {
            System.out.println(t);
        }


    }

    void newSentence(String[] source, String word)
    {
        LinkedList<String> sentence = new LinkedList<>();









        if(!word.trim().isEmpty())
        {
            for (int i = 0; i < word.length(); i++)
            {
                char c = word.toLowerCase().trim().charAt(i);

                for (int j = 0; j < source.length; j++)
                {

                    if(source[j].indexOf(c) == i )
                    {
                        sentence.add(source[j] +" ");
                        break;
                    }



                }

            }



        }
        else
            {
                System.out.println("You need to insert a word here !");;
            }
        System.out.println("You inserted: " + word);

        for (int i = 0; i < sentence.size(); i++)
        {
            System.out.print(sentence.get(i));
        }
        System.out.println("");



    }









}
