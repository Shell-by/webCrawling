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
		File file = new File("test/test.txt");// ������ ����Ǵ� ��ġ
		Scanner in = new Scanner(System.in);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

																												// �Ǵ� ��ũ
			// System.out.println(doc.html());
			System.out.println("������� ������ ������ ���ÿ� \n(��ü���/����/������/����/��ȸ/��ġ/������/IT����/�缳)");// ������� ������ ������ ���ϴ� ��
			String kinds = in.nextLine();
			String link = null;
			switch (kinds) {
			case ("��ü���"):
				link = "total";
				break;
			case ("����"):
				link = "money";
				break;
			case ("������"):
				link = "sports";
				break;
			case ("����"):
				link = "star";
				break;
			case ("��ȸ"):
				link = "life";
				break;
			case ("��ġ"):
				link = "politics";
				break;
			case ("������"):
				link = "world";
				break;
			case ("IT����"):
				link = "it";
				break;
			case ("�缳"):
				link = "opinion";
				break;
			}
			Document doc = Jsoup.connect("https://rss.joins.com/sonagi/joins_sonagi_"+link+"_list.xml").get();// ������� ������ ���� ��ũ�� ��
			Elements title = doc.select("title");
			ArrayList<String> list = new ArrayList<String>();
			System.out.println("�����ϰ� �����ܾ �ֽ��ϱ�? (��/�ƴϿ�)");// �����ϰ� ���� �ܾ �ִ��� ����

			String y = in.nextLine();// "��"��"�ƴϿ�"��� ���� ����
			if (y.equals("��")) { // "��"��� �������� �����
				System.out.println("�����ϰ� ���� �ܾ �Է��ϼ���");// �����ϰ� ���� �ܾ ���
				String n = in.nextLine();// �����ϰ� ���� �ܾ �Է�
				System.out.println(kinds);// ������ ������ �˷���
				for (int i = 2; i < title.size(); i++) {
					if (title.get(i).text().contains(n))// �����ϰ� ���� �ܾ ������ ������� �ʰ� �Ѿ
						continue;
					list.add(title.get(i).text());// ArrayList�� ���� ������ ����
					System.out.println(title.get(i).text());// ���� ���� ���
				}
				bw.write(kinds);// ���� ó���� �ѹ� ���� ������ �Է���
				for (int i = 2; i < title.size(); i++) {
					bw.write(title.get(i).text());// ���� ������ �Է���
					bw.newLine();// ���� ������ �Է��ϰ� ���� �ٲ�
				}
				bw.close();
				osw.close();
				fos.close();
			} else if (y.equals("�ƴϿ�")) {// "�ƴϿ�"��� �������� �����
				System.out.println(kinds);
				for (int i = 2; i < title.size(); i++) {
					list.add(title.get(i).text());// ArrayList�� ���� ������ ����
					System.out.println(title.get(i).text());// ���� ���� ���
				}
				bw.write(kinds);// ���� ó���� �ѹ� ���� ������ �Է���
				for (int i = 2; i < title.size(); i++) {
					bw.write(title.get(i).text());// ���� ������ �Է���
					bw.newLine();// ���� ������ �Է��ϰ� ���� �ٲ�
				}
				bw.close();
				osw.close();
				fos.close();
			} else {// �ٸ� ����� ������ �����
				System.out.println("����");
			}

		} catch (Exception e) {
			System.out.println("����");
		}
	}

}
