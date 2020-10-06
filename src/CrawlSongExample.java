import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlSongExample {
    public static void main(String[] args) {
        try {
            //Sử dụng URL của java.net để khởi tạo đường dẫn thư viện nhạc muốn lấy danh sách bài hát
            URL url = new URL("https://www.nhaccuatui.com/bai-hat/nhac-tre-moi.html");
            // open the stream and put it into BufferedReader
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
            // openStream(): Opens a connection to this URL and returns an InputStream for reading from that connection.
            // Scanner(InputStream source): Constructs a new Scanner that produces values scanned from the specified input stream.
            // InputStreamReader: hàm khởi tạo truyền một inputstream vào.
            scanner.useDelimiter("\\Z");
            // useDelimiter(String pattern) phương thức useDelimiter() để chỉ định các kí tự dùng làm kí tự phân cách.
            //Sets this scanner's delimiting pattern to a pattern constructed from the specified String.
            String content = scanner.next();
            //next(): Finds and returns the next complete token from this scanner.
            // lấy về form:
            // <a href="https://www.nhaccuatui.com/bai-hat/chang-the-giu-lay-giang-nv.Xu6vqiiovB0g.html"
            // class="name_song" title="Chẳng Thể Giữ Lấy - Giang NV" onclick=" _gaq.push(['_trackEvent', 'Home', 'Click', 'Bai Hat Moi']);
            // lt(['home','bai-hat-moi','click','song','Xu6vqiiovB0g','','title']);">Chẳng Thể Giữ Lấy</a>
            scanner.close();
            // remove all new line
            // lấy được cái tag rồi dùng replace để xóa hết những cái thừa đi, còn lại mỗi tên thôi
            content = content.replaceAll("\\n+", "");
            // regex

            Pattern p = Pattern.compile("name_song\">(.*?)</a>");
            Matcher m = p.matcher(content);
            while (m.find()) {
                System.out.println(m.group(1));
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
