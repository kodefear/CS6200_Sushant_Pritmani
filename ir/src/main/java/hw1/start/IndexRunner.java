package hw1.start;

import hw1.elasticAPI.BulkIndex;
import hw1.json.JsonGenerator;
import hw1.parser.JsoupParser;
import org.elasticsearch.client.Response;
import org.jsoup.select.Elements;

import java.io.File;

/**
 * Created by Sushant on 5/12/2017.
 */
public class IndexRunner {

    public static void main(String[] args){

        String dir = "C://Users//Sushant//Desktop//IR//data//AP89_DATA//AP_DATA//ap89_collection";
        File[] files = new File(dir).listFiles();
        for(File f : files){
            if(f.isFile() && !f.getName().equalsIgnoreCase("readme")){
               Elements doc =JsoupParser.getTagFromDoc(JsoupParser.parseDoc(f.getAbsolutePath()),"DOC");
               String jsonFile = JsonGenerator.getJsonObject(doc,"hw1", "ap_dataset");
               Response fileResponse = BulkIndex.createIndexRequest(jsonFile, "ap_dataset");
               System.out.println(fileResponse.getStatusLine().toString());


            }

        }

    }
}
