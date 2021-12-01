import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class Main {

	public static void main(String[] args) {
		File file = new File("test/test.txt");// 파일이 저장되는 위치
		Scanner in = new Scanner(System.in);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

																												// 되는 링크
			// System.out.println(doc.html());
			System.out.println("보고싶은 뉴스의 종류를 고르시오 \n(전체기사/경제/스포츠/연예/사회/정치/지구촌/IT과학/사설)");// 보고싶은 종류의 뉴스를 정하는 것
			String kinds = in.nextLine();
			String link = null;
			switch (kinds) {
			case ("전체기사"):
				link = "total";
				break;
			case ("경제"):
				link = "money";
				break;
			case ("스포츠"):
				link = "sports";
				break;
			case ("연예"):
				link = "star";
				break;
			case ("사회"):
				link = "life";
				break;
			case ("정치"):
				link = "politics";
				break;
			case ("지구촌"):
				link = "world";
				break;
			case ("IT과학"):
				link = "it";
				break;
			case ("사설"):
				link = "opinion";
				break;
			}
			Document doc = Jsoup.connect("https://rss.joins.com/sonagi/joins_sonagi_"+link+"_list.xml").get();// 보고싶은 종류의 뉴스 링크가 됨
			Elements title = doc.select("title");
			ArrayList<String> list = new ArrayList<String>();
			System.out.println("제외하고 싶은단어가 있습니까? (예/아니요)");// 제외하고 싶은 단어가 있는지 물음

			String y = in.nextLine();// "예"와"아니요"라는 답을 받음
			if (y.equals("예")) { // "예"라고 답했을떄 실행됨
				System.out.println("제외하고 싶은 단어를 입력하세요");// 제외하고 싶은 단어를 물어봄
				String n = in.nextLine();// 제외하고 싶은 단어를 입력
				System.out.println(kinds);// 뉴스의 종류를 알려줌
				for (int i = 2; i < title.size(); i++) {
					if (title.get(i).text().contains(n))// 제외하고 싶은 단어가 왔을때 출력하지 않고 넘어감
						continue;
					list.add(title.get(i).text());// ArrayList에 뉴스 제목을 넣음
					System.out.println(title.get(i).text());// 뉴스 제목 출력
				}
				bw.write(kinds);// 파일 처음에 한번 뉴스 종류를 입력함
				for (int i = 2; i < title.size(); i++) {
					bw.write(title.get(i).text());// 뉴스 제목을 입력함
					bw.newLine();// 뉴스 제목을 입력하고 줄을 바꿈
				}
				bw.close();
				osw.close();
				fos.close();
			} else if (y.equals("아니요")) {// "아니요"라고 답했을떄 실행됨
				System.out.println(kinds);
				for (int i = 2; i < title.size(); i++) {
					list.add(title.get(i).text());// ArrayList에 뉴스 제목을 넣음
					System.out.println(title.get(i).text());// 뉴스 제목 출력
				}
				bw.write(kinds);// 파일 처음에 한번 뉴스 종류를 입력함
				for (int i = 2; i < title.size(); i++) {
					bw.write(title.get(i).text());// 뉴스 제목을 입력함
					bw.newLine();// 뉴스 제목을 입력하고 줄을 바꿈
				}
				bw.close();
				osw.close();
				fos.close();
			} else {// 다른 대답을 했을떄 실행됨
				System.out.println("오류");
			}

		} catch (Exception e) {
			System.out.println("실패");
		}
	}

}
