/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package fun.fifu.huohua;


import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.event.internal.EventInternalJvmKt;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageUtils;
import net.mamoe.mirai.utils.BotConfiguration;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


public class App {
    public App getPlugin() {
        return this;
    }

    public static Bot b;
    public static List<Long> list = new ArrayList<Long>();
    public static QQMan q = null;
    public static long zxb = 3513353936L;
    public static final ConfigPojo config = new ConfigPojo().readConfig();

    public App(){
        zxb = config.getOwnerId();
    }
//
//    public static void main(String[] args) throws IOException {
//
//    }
//

    public static void main(String[] args) {
        String config_path = "config.yml";

        try {
            q = new QQMan(config_path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        list.addAll(q.getOpenGroup());
        b = BotFactoryJvm.newBot(config.getBotId(), config.getBotPassword(), new BotConfiguration() {
            {
                fileBasedDeviceInfo("deviceInfo.json");
            }
        });
        b.login();
        EventInternalJvmKt._subscribeEventForJaptOnly(GroupMessageEvent.class, b, e -> {
            if (!list.contains(e.getGroup().getId()))
                return;
            String mes = e.getMessage().contentToString();

            //Qa
            Qa qa = new Qa();
            String a = qa.qa(mes);
            String tryR = qa.tryAdd(mes);
            if (!tryR.equals("")) {
                e.getGroup().sendMessage(tryR);
            }
            if (!a.equalsIgnoreCase("")) {
                e.getGroup().sendMessage(a);
            }

            if (mes.equals("截图")) {
                try {
                    File file = captureScreen("./owo", "owo.png");
                    final Image image = e.getGroup().uploadImage(file);
                    // 上传一个图片并得到 Image 类型的 Message
                    final String imageId = image.getImageId(); // 可以拿到 ID
                    final Image fromId = MessageUtils.newImage(imageId); // ID 转换得到 Image
                    e.getGroup().sendMessage(image); // 发送图片
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else if (mes.equals("截屏")) {
                try {
                    File file = captureScreen2("./owo", "owo.png");
                    final Image image = e.getGroup().uploadImage(file);
                    // 上传一个图片并得到 Image 类型的 Message
                    final String imageId = image.getImageId(); // 可以拿到 ID
                    final Image fromId = MessageUtils.newImage(imageId); // ID 转换得到 Image
                    e.getGroup().sendMessage(image); // 发送图片
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else if (mes.equals("关闭") && e.getSender().getId() == zxb) {
                e.getGroup().sendMessage("正在关闭");
                System.exit(1);
            } else if (mes.equals("重载") && e.getSender().getId() == zxb) {
                StringBuilder sb = new StringBuilder();
                sb.append("原：\n").append(list).append("\n");
                sb.append("正在重载配置文件\n");
                try {
                    q = new QQMan(config_path);
                } catch (Exception exception) {
                    sb.append("重载失败：").append(exception).append("\n");
                    e.getGroup().sendMessage(sb.toString());
                    return;
                    //fileNotFoundException.printStackTrace();
                }
                list.clear();
                list.addAll(q.getOpenGroup());
                sb.append("完毕\n");
                sb.append("现：\n" + list);
                e.getGroup().sendMessage(sb.toString());
            }
        });
        try {
            captureScreen("./owo", "owo.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (q.isAutoMorning()) {
            if (belongCalendar("6:00", "10:00")) {
                zao(q);
            }
        }
        /*

        try {


            Terminal terminal = TerminalBuilder.builder()
                    .system(true)
                    .build();

            LineReader lineReader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .build();
            String prompt = "owo>";
            while (true) {
                String line;
                line = lineReader.readLine(prompt);
                switch (line) {
                    case "zao":
                        System.out.println("早~");
                        zao(q);
                        System.out.println("早owo");
                        break;
                    case "owo":
                        System.out.println("小白最帅");
                        break;
                    case "stop":
                        System.out.println("正在关闭");
                        System.exit(0);
                        break;
                    case "reload":
                        System.out.println("原：" + list);
                        System.out.println("正在重载配置文件");
                        q = new QQMan(config_path);
                        list.clear();
                        list.addAll(q.getOpenGroup());
                        System.out.println("完毕");
                        System.out.println("现：" + list);
                        break;
                    default:
                        System.out.println(line);
                        break;
                }

            }
        }catch (Exception eee){
            System.out.println(eee);
        }*/
        while (true) {
            try {
                Thread.sleep(999999999);
            } catch (Exception ee) {
                System.out.println(ee);
            }
        }
    }

    public static void zao(QQMan qqman) {
        for (QQMan.QQ q : qqman.getQQList()) {
            b = BotFactoryJvm.newBot(q.getqq(), q.getpasswd(), new BotConfiguration() {
                {
                    fileBasedDeviceInfo("deviceInfo.json");
                }
            });
            b.login();
            for (Friend f : b.getFriends()) {
                if (!q.ining(f.getId()))
                    f.sendMessage(qqman.getOneMorning());
            }
        }
    }

    public static File captureScreen(String fileName, String folder) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        // 截图保存的路径
        File screenFile = new File(fileName);
        System.out.println(screenFile);
        // 如果路径不存在,则创建
        if (!screenFile.exists()) {
            screenFile.getParentFile().mkdirs();
        }
        //判断文件是否存在，不存在就创建文件
        if (!screenFile.exists() && !screenFile.isDirectory()) {
            screenFile.mkdir();
        }

        File f = new File(screenFile, folder);
        ImageIO.write(image, "png", f);
        return f;
    }


    public static File captureScreen2(String fileName, String folder) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        // 截图保存的路径
        File screenFile = new File(fileName);
        System.out.println(screenFile);
        // 如果路径不存在,则创建
        if (!screenFile.exists()) {
            screenFile.getParentFile().mkdirs();
        }
        //判断文件是否存在，不存在就创建文件
        if (!screenFile.exists() && !screenFile.isDirectory()) {
            screenFile.mkdir();
        }

        File f = new File(screenFile, folder);
        ImageIO.write(image, "png", f);
        rotateImage270(f);
        return f;
    }
    //右转90度
    public static File rotateImage270(File file) throws Exception
    {
        BufferedImage bufferedimage = ImageIO.read(file);
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(h, w, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(90), (w >> 1) - ((w - h) >> 1), h >> 1);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        ImageIO.write(img,"png",file);
        return file;
    }

    /**
     * 判断时间是否在时间段内
     * begin 06:00
     * end 10:00
     */
    public static boolean belongCalendar(String begin, String end) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");// 设置日期格式
        Date nowTime = null;
        Date beginTime = null;
        Date endTime = null;
        try {
            nowTime = df.parse(df.format(new Date()));
            beginTime = df.parse(begin);
            endTime = df.parse(end);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar Cbegin = Calendar.getInstance();
        Cbegin.setTime(beginTime);

        Calendar Cend = Calendar.getInstance();
        Cend.setTime(endTime);

        return date.after(Cbegin) && date.before(Cend);
    }

}
