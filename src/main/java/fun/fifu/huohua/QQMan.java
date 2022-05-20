package fun.fifu.huohua;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class QQMan {
    public class QQ {
        private long qq;
        private String passwd;
        private List<Long> ing;

        public QQ(long qq, String passwd, List<Long> ing) {
            this.qq = qq;
            this.passwd = passwd;
            this.ing = ing;
        }

        public long getqq() {
            return qq;
        }

        public String getpasswd() {
            return passwd;
        }

        public List geting() {
            return ing;
        }

        public boolean ining(long q) {
            for (long l : ing) {
                if (l == q)
                    return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "QQ{" +
                    "qq=" + qq +
                    ", passwd='" + passwd + '\'' +
                    ", ing=" + ing +
                    '}';
        }
    }

    private List<QQ> QQList;
    private List<Long> openGroup;
    private List<String> goodMorning;
    private boolean autoMorning;

    public QQMan(String path_yml) throws FileNotFoundException {
        build(path_yml);
    }

    public List<QQ> build(String path_yml) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        File f = new File(path_yml);
        Object result = yaml.load(new FileInputStream(f));
        openGroup = new ArrayList<>();
        for (Object o : (List) ((Map) result).get("OpenGroup")) {
            Long group = Long.parseLong(o.toString());
            openGroup.add(group);
        }
        goodMorning = new ArrayList<>();
        for (Object o : (List) ((Map) result).get("GoodMorning")) {
            String morning = o.toString();
            goodMorning.add(morning);
        }
        autoMorning = (boolean) ((Map) result).get("AutoMorning");
        List<?> login = (List) ((Map) result).get("Login");
        List<QQ> qqList = new ArrayList<>();
        for (Object m : login) {
            Map map = (Map) m;
            long qq = Long.parseLong(map.get("qq").toString());
            String passwd = map.get("passwd").toString();
            List<?> ing = (List) ((Map) m).get("ing");
            List<Long> in = new ArrayList<>();
            for (Object i : ing) {
                in.add(Long.parseLong(i.toString()));
            }
            qqList.add(new QQ(qq, passwd, in));
        }
        QQList = qqList;
        return QQList;
    }

    public List<Long> getOpenGroup() {
        return openGroup;
    }

    public String getOneMorning() {
        return goodMorning.get(new Random().nextInt(goodMorning.size()));
    }

    public boolean isAutoMorning() {
        return autoMorning;
    }

    public List<String> getMorning() {
        return goodMorning;
    }

    public List<QQ> getQQList() {
        return QQList;
    }

    public QQ get(long qq) {
        for (QQ q : QQList) {
            if (q.getqq() == qq)
                return q;
        }
        return null;
    }


}
