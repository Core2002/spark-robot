package fun.fifu.huohua;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class QaMan {
    private Map<String, String> kv;
    private String qaPath;

    public QaMan(String Qa_path) {
        kv = new HashMap<>();
        build(Qa_path);
    }

    public void build(String Qa_path) {
        qaPath = Qa_path;
        Yaml yaml = new Yaml();
        File f = new File(Qa_path);
        Object result = null;
        try {
            result = yaml.load(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            try {
                f.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        Map mp = (Map) result;
        if(mp==null)
            return;
        for (Object ok : mp.keySet()) {
            String k = ok.toString();
            String v = mp.get(k).toString();
            kv.put(k, v);
        }
    }

    public String getA(String q) {
        String a = kv.get(q);
        if (a != null && !a.equalsIgnoreCase("")) {
            return a;
        } else {
            return "";
        }
    }

    public void put(String q, String a) {
        kv.put(q, a);
    }

    public void save() throws IOException {
        IOTools.writeTextFile(new Yaml().dump(kv),"utf-8",qaPath);
    }

    @Override
    public String toString() {
        return "QaMan{" +
                "kv=" + kv +
                '}';
    }
}
