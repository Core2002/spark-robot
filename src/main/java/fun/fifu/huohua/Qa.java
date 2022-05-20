package fun.fifu.huohua;

import java.io.IOException;

public class Qa {
    public static QaMan qaMan;

    public Qa() {
        qaMan = new QaMan("Qa.yml");
    }

    public String qa(String q) {
        return qaMan.getA(q);
    }

    public String tryAdd(String tryStr) {
        if (tryStr.charAt(0) != '问' || !tryStr.contains("答"))
            return "";
        String w = "";
        String d = "";
        int id = tryStr.indexOf("答");
        w = tryStr.substring(1, id);
        d = tryStr.substring(id + 1);
        qaMan.put(w, d);
        try {
            qaMan.save();
        } catch (IOException e) {
            e.printStackTrace();
            return "添加失败：\n" + e;
        }
        return "添加成功";
    }
}
