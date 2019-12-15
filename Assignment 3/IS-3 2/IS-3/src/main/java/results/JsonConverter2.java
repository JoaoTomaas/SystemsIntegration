package results;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Arrays;


public class JsonConverter2 {
    public static String converte(String s,String id){
        //ver os opcional
        String[] parts1 = s.split("\t");
        System.out.println("DEBUG hahah "+ Arrays.toString(parts1));
        JSONObject result = new JSONObject();
        JSONObject schema_child = new JSONObject();
        JSONObject payload_child = new JSONObject();
        JSONObject campo1 = new JSONObject();
        JSONObject campo4 = new JSONObject();
        JSONObject campo2 = new JSONObject();
        JSONObject campo3 = new JSONObject();

        JSONArray array_do_mal = new JSONArray();

        campo1.put("type","double");
        campo1.put("optional",true);
        campo1.put("field","revenue");

        campo2.put("type","double");
        campo2.put("optional",true);
        campo2.put("field","expenses");

        campo3.put("type","double");
        campo3.put("optional",true);
        campo3.put("field","profit");

        campo4.put("type","double");
        campo4.put("optional",false);
        campo4.put("field","id_item");


        array_do_mal.put(campo1);
        array_do_mal.put(campo2);
        array_do_mal.put(campo3);
        array_do_mal.put(campo4);


        schema_child.put("name","total data");
        schema_child.put("optional",false);
        schema_child.put("type","struct");
        schema_child.put("fields",array_do_mal);
        result.put("schema",schema_child);


        payload_child.put("revenue",Double.parseDouble(parts1[0]));
        payload_child.put("expenses",Double.parseDouble(parts1[1]));
        payload_child.put("profit",Double.parseDouble(parts1[2]));
        payload_child.put("id_item",Integer.parseInt(id));

        result.put("payload",payload_child);

        String jsonresult = result.toString();
        //System.out.println(jsonresult+" \n\n");
        return jsonresult;

    }

    public static String converte_total(String s,String id){
        String[] parts1 = s.split("\t");
        // System.out.println("DEBUG"+Arrays.toString(parts1));
        JSONObject result = new JSONObject();
        JSONObject schema_child = new JSONObject();
        JSONObject payload_child = new JSONObject();
        JSONObject campo1 = new JSONObject();
        JSONObject campo4 = new JSONObject();
        JSONObject campo2 = new JSONObject();
        JSONObject campo3 = new JSONObject();

        JSONArray array_do_mal = new JSONArray();

        campo1.put("type","double");
        campo1.put("optional",true);
        campo1.put("field","revenue");

        campo2.put("type","double");
        campo2.put("optional",true);
        campo2.put("field","expenses");

        campo3.put("type","double");
        campo3.put("optional",true);
        campo3.put("field","profit");

        campo4.put("type","double");
        campo4.put("optional",false);
        campo4.put("field","id_item");


        array_do_mal.put(campo1);
        array_do_mal.put(campo2);
        array_do_mal.put(campo3);
        array_do_mal.put(campo4);


        schema_child.put("name","total data");
        schema_child.put("optional",false);
        schema_child.put("type","struct");
        schema_child.put("fields",array_do_mal);
        result.put("schema",schema_child);


        payload_child.put("revenue",Double.parseDouble(parts1[0]));
        payload_child.put("expenses",Double.parseDouble(parts1[1]));
        payload_child.put("profit",Double.parseDouble(parts1[2]));
        payload_child.put("id_item",(1));

        result.put("payload",payload_child);

        String jsonresult = result.toString();
        //System.out.println(jsonresult+" \n\n");
        return jsonresult;

    }

    public static String converte_avg(String s,String id){
        //ver os opcional
        // System.out.println("DEBUG"+Arrays.toString(parts1));
        System.out.println("HAAAAAAAAAA"+s+"    "+id);
        JSONObject result = new JSONObject();
        JSONObject schema_child = new JSONObject();
        JSONObject payload_child = new JSONObject();
        JSONObject campo1 = new JSONObject();
        JSONObject campo2 = new JSONObject();

        JSONArray array_do_mal = new JSONArray();

        campo1.put("type","double");
        campo1.put("optional",true);
        campo1.put("field","average_amount");


        campo2.put("type","double");
        campo2.put("optional",false);
        campo2.put("field","id_item");


        array_do_mal.put(campo1);
        array_do_mal.put(campo2);


        schema_child.put("name","total data teste");
        schema_child.put("optional",false);
        schema_child.put("type","struct");
        schema_child.put("fields",array_do_mal);
        result.put("schema",schema_child);


        payload_child.put("average_amount",Double.parseDouble(calcMedia(s)));
        payload_child.put("id_item",Integer.parseInt(id));

        result.put("payload",payload_child);

        String jsonresult = result.toString();
        return jsonresult;

    }

    public static String converte_avg_total(String s,String id){
        //ver os opcional
        String[] parts1 = s.split("\t");
        // System.out.println("DEBUG"+Arrays.toString(parts1));
        JSONObject result = new JSONObject();
        JSONObject schema_child = new JSONObject();
        JSONObject payload_child = new JSONObject();
        JSONObject campo1 = new JSONObject();
        JSONObject campo2 = new JSONObject();

        JSONArray array_do_mal = new JSONArray();

        campo1.put("type","double");
        campo1.put("optional",true);
        campo1.put("field","average_amount");


        campo2.put("type","double");
        campo2.put("optional",false);
        campo2.put("field","id_item");


        array_do_mal.put(campo1);
        array_do_mal.put(campo2);


        schema_child.put("name","total data teste");
        schema_child.put("optional",false);
        schema_child.put("type","struct");
        schema_child.put("fields",array_do_mal);
        result.put("schema",schema_child);


        payload_child.put("average_amount",Double.parseDouble(calcMedia(s)));
        payload_child.put("id_item",1);

        result.put("payload",payload_child);

        String jsonresult = result.toString();
        return jsonresult;
    }
    public static String converte_highestprof(String s,String id){
        //ver os opcional
        String[] parts1 = s.split("\t");
        System.out.println("DEBUG"+Arrays.toString(parts1));
        JSONObject result = new JSONObject();
        JSONObject schema_child = new JSONObject();
        JSONObject payload_child = new JSONObject();
        JSONObject campo1 = new JSONObject();
        JSONObject campo2 = new JSONObject();

        JSONArray array_do_mal = new JSONArray();

        campo1.put("type","double");
        campo1.put("optional",true);
        campo1.put("field","highest_profit");


        campo2.put("type","double");
        campo2.put("optional",false);
        campo2.put("field","id_item");


        array_do_mal.put(campo1);
        array_do_mal.put(campo2);


        schema_child.put("name","total data teste");
        schema_child.put("optional",false);
        schema_child.put("type","struct");
        schema_child.put("fields",array_do_mal);
        result.put("schema",schema_child);


        payload_child.put("highest_profit",Double.parseDouble(parts1[1]));
        payload_child.put("id_item",Integer.parseInt(parts1[0]));

        result.put("payload",payload_child);

        String jsonresult = result.toString();
        return jsonresult;

    }
    public static String converte_pais(String s,String id){
        System.out.println("-----------------------");
        System.out.println(s);
        System.out.println(id);
        JSONObject aux = new JSONObject(s);
        //ver os opcional
        JSONObject result = new JSONObject();
        JSONObject schema_child = new JSONObject();
        JSONObject payload_child = new JSONObject();
        JSONObject campo1 = new JSONObject();
        JSONObject campo2 = new JSONObject();
        JSONObject campo3 = new JSONObject();

        JSONArray array_do_mal = new JSONArray();

        campo1.put("type","double");
        campo1.put("optional",true);
        campo1.put("field","produto");

        campo3.put("type","string");
        campo3.put("optional",true);
        campo3.put("field","pais");


        campo2.put("type","double");
        campo2.put("optional",false);
        campo2.put("field","id_item");


        array_do_mal.put(campo1);
        array_do_mal.put(campo2);
        array_do_mal.put(campo3);


        schema_child.put("name","total data teste");
        schema_child.put("optional",false);
        schema_child.put("type","struct");
        schema_child.put("fields",array_do_mal);
        result.put("schema",schema_child);

        payload_child.put("produto",aux.getDouble("produto"));
        payload_child.put("pais",aux.getString("pais"));
        payload_child.put("id_item",Integer.parseInt(id));

        result.put("payload",payload_child);

        String jsonresult = result.toString();
        return jsonresult;

    }

    public static String converte_windoh(String s,String id){
        //ver os opcional
        String[] parts1 = s.split("\t");
        System.out.println("DEBUG hahah "+ Arrays.toString(parts1));
        JSONObject result = new JSONObject();
        JSONObject schema_child = new JSONObject();
        JSONObject payload_child = new JSONObject();
        JSONObject campo1 = new JSONObject();
        JSONObject campo4 = new JSONObject();
        JSONObject campo2 = new JSONObject();
        JSONObject campo3 = new JSONObject();

        JSONArray array_do_mal = new JSONArray();

        campo1.put("type","double");
        campo1.put("optional",true);
        campo1.put("field","revenue");

        campo2.put("type","double");
        campo2.put("optional",true);
        campo2.put("field","expenses");

        campo3.put("type","double");
        campo3.put("optional",true);
        campo3.put("field","profit");

        campo4.put("type","double");
        campo4.put("optional",false);
        campo4.put("field","id_item");


        array_do_mal.put(campo1);
        array_do_mal.put(campo2);
        array_do_mal.put(campo3);
        array_do_mal.put(campo4);


        schema_child.put("name","total data");
        schema_child.put("optional",false);
        schema_child.put("type","struct");
        schema_child.put("fields",array_do_mal);
        result.put("schema",schema_child);


        payload_child.put("revenue",Double.parseDouble(parts1[0]));
        payload_child.put("expenses",Double.parseDouble(parts1[1]));
        payload_child.put("profit",Double.parseDouble(parts1[2]));
        payload_child.put("id_item",1);

        result.put("payload",payload_child);

        String jsonresult = result.toString();
        //System.out.println(jsonresult+" \n\n");
        return jsonresult;

    }
    public static String calcMedia(String parse){
        String[] separa = parse.split(",");
        Double media = Double.parseDouble(separa[1]) / Integer.parseInt(separa[0]);

        return media + "";
    }
}
