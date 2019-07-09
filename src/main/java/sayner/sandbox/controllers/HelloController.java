package sayner.sandbox.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sayner.sandbox.dto.ArticleDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String sayHi() {
        return "Hi";
    }

   /* @RequestMapping("/")
    public String index() {

        Runnable but_does_it_work = () -> System.out.println("BUT DOES IT WORK");
        but_does_it_work.run();

        List<String> messages = new ArrayList<String>();
        messages.add("message");
        messages.add("msg");
        messages.add("messages");

        int max = 0;
        for(String mes : messages){

            String[] array1 = {"мама", "мыла", "раму"};
            String[] array2 = {"я", "очень", "люблю", "java"};
            String[] array3 = {"мир", "труд", "май"};

            List<String[]> arrays = new ArrayList<>();
            arrays.add(array1);
            arrays.add(array2);
            arrays.add(array3);

            Comparator<String[]> sortByLength = new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    return o1.length - o2.length;
                }
            };

            Comparator<String[]> sortByWordsLength = new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    int length1 = 0;
                    int length2 = 0;
                    for (String s : o1) {
                        length1 += s.length();
                    }
                    for (String s : o2) {
                        length2 += s.length();
                    }
                    return length1 - length2;
                }
            };

            arrays.sort(sortByLength);
        }

        return "Welcome";

    }*/

    @RequestMapping("/getWithoutRequestParam")
    public List<Object> getWithoutRequestParam(ArticleDTO personDTO) {
        return Arrays.asList(
                personDTO.getAnyDate(),
                personDTO.getId(),
                personDTO.getName());
    }
}
