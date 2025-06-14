package priceCalculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class InfoList {
	private static InfoList infoList;

	// ���� ���
	private String folderPath = "C:/GiraffeCalculator";
	private String pricePath = folderPath + "/price.txt";
	private String specPath = folderPath + "/specification.txt";
	private File folder = new File(folderPath);
	private File price = new File(pricePath);
	private File spec = new File(specPath);
	
	// ���� ���� ����
	boolean isPriceNew = false;
	boolean isSpecNew = false;
		
	// �Ϲ� ���۹�		10����
	private String[] cropsName = { "�丶��", "����", "������", "����", "����", "ȩ", "����", "�����", "����", "���ξ���"};
	private HashMap<String, Double> cropsMap = new HashMap<>();
		
	// ���õ� ���۹�	15����
	private String[] pCropsName = { "ī������", "��纣��", "������", "����", "����", "���ľ�", "ü��", "������", "���ڳ�", "��ġ", "�θ���", "Ű��", "�ƺ�ī��", "�����", "���ƹ�" };
	private HashMap<String, Double> pCropsMap = new HashMap<>();
		
	// �Ϲ� �����		13����
	private String[] fishName = { "�ޱ�", "���", "�ݺؾ�", "����", "����ġ", "���", "����", "�ٶ���", "����", "����ġ", "�׾�", "���", "��������"};
	private HashMap<String, Double> fishMap = new HashMap<>();
		
	// ���õ� �����	9����
	private String[] pFishName = {"�����", "���ĸ�", "������", "�Ʊ�", "�ٵ�", "������", "�򵿰���", "�����", "������" };
	private HashMap<String, Double> pFishMap = new HashMap<>();
	
	// �丮��
	// 1��� ����ġ, 2��� ����ġ, 3��� ����ġ, ��� �̼Ҹ� Ȯ��, �丮�� Ż Ȯ��, �丮 �뼺�� Ȯ��, ���� �ߵ� Ȯ��
	private int cookLevel = 1;
	// ����, ���̽�
	private String[] cookStatueName = { "1��� ����ġ", "2��� ����ġ", "��� �̼Ҹ� Ȯ��", "�丮�� Ż Ȯ��", "�丮 �뼺�� Ȯ��", "���� �ߵ� Ȯ��" };
	private String[] cookdiceName = { "1��� ����ġ", "2��� ����ġ", "�丮�� Ż Ȯ��" };
	private HashMap<String, Double> statueCook = new HashMap<>();
	private HashMap<String, Double> diceCook = new HashMap<>();
	
	// ����
	// ����� �߰� ȹ��� ����, ��ȭ�� ���� ȹ��� ����, ������ ���� ȹ��� ����
	private int minerLevel = 1;
	// ����, ���̽�
	private String[] minerStatueName = { "����� �߰� ȹ��� ����" , "��ȭ�� ���� ȹ��� ����" , "������ ���� ȹ��� ����"};
	private String[] minerDiceName = { "����� �߰� ȹ��� ����" , "��ȭ�� ���� ȹ��� ����" , "������ ���� ȹ��� ����"};
	private HashMap<String, Double> statueMiner = new HashMap<>();
	private HashMap<String, Double> diceMiner = new HashMap<>();
		
	private InfoList() {
		try {
			// ���� �� ������ �������� ������ ����
	        if(!folder.exists())
	        	folder.mkdir();
	        if(!price.exists()) {
	        	isPriceNew = true;
	        	price.createNewFile();
	        }
	        if(!spec.exists()) {
	        	isSpecNew = true;
	        	spec.createNewFile();
	        }
		} catch(IOException e) {
			e.printStackTrace();
		}
			
		String line;
		String job = "";
		String mode = "";
		
		if(isPriceNew) {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(pricePath))) {
				bw.write("�Ϲ� ���۹�\n");
				for(int i = 0; i < cropsName.length;i++)
					bw.write(cropsName[i] + ":0\n");
				bw.write("���õ� ���۹�\n");
				for(int i = 0; i < pCropsName.length;i++)
					bw.write(pCropsName[i] + ":0\n");
				bw.write("�Ϲ� �����\n");
				for(int i = 0; i < fishName.length;i++)
					bw.write(fishName[i] + ":0\n");
				bw.write("���õ� �����\n");
				for(int i = 0; i < pFishName.length;i++)
					bw.write(pFishName[i] + ":0\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// ���۹�, ����� �ü� �ҷ�����
		try (BufferedReader br = new BufferedReader(new FileReader(pricePath))) {
			while ((line = br.readLine()) != null) {
				if(line.equals("�Ϲ� ���۹�"))
					mode = "�Ϲ� ���۹�";
				else if(line.equals("���õ� ���۹�"))
					mode = "���õ� ���۹�";
				else if(line.equals("�Ϲ� �����"))
					mode = "�Ϲ� �����";
				else if(line.equals("���õ� �����"))
					mode = "���õ� �����";
				else {
					if(mode.equals("�Ϲ� ���۹�"))
						cropsMap.put(line.split(":")[0], Double.parseDouble(line.split(":")[1]));
					if(mode.equals("���õ� ���۹�"))
						pCropsMap.put(line.split(":")[0], Double.parseDouble(line.split(":")[1]));
					if(mode.equals("�Ϲ� �����"))
						fishMap.put(line.split(":")[0], Double.parseDouble(line.split(":")[1]));
					if(mode.equals("���õ� �����"))
						pFishMap.put(line.split(":")[0], Double.parseDouble(line.split(":")[1]));
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		if(isSpecNew) {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(specPath))) {
				bw.write("�丮��\n");
				bw.write("���õ�:1\n");
				bw.write("����\n");
				bw.write("1��� ����ġ:0.0\n");
				bw.write("2��� ����ġ:0.0\n");
				bw.write("��� �̼Ҹ� Ȯ��:0.0\n");
				bw.write("�丮�� Ż Ȯ��:0.0\n");
				bw.write("�丮 �뼺�� Ȯ��:0.0\n");
				bw.write("���� �ߵ� Ȯ��:0.0\n");
				bw.write("���̽�\n");
				bw.write("1��� ����ġ:0.0\n");
				bw.write("2��� ����ġ:0.0\n");
				bw.write("�丮�� Ż Ȯ��:0.0\n");
				bw.write("����\n");
				bw.write("���õ�:1\n");
				bw.write("����� �߰� ȹ��� ����:0.0\n");
				bw.write("��ȭ�� ���� ȹ��� ����:0.0\n");
				bw.write("������ ���� ȹ��� ����:0.0\n");
				bw.write("����\n");
				bw.write("����� �߰� ȹ��� ����:0.0\n");
				bw.write("��ȭ�� ���� ȹ��� ����:0.0\n");
				bw.write("������ ���� ȹ��� ����:0.0\n");
				bw.write("���̽�\n");
				bw.write("����� �߰� ȹ��� ����:0.0\n");
				bw.write("��ȭ�� ���� ȹ��� ����:0.0\n");
				bw.write("������ ���� ȹ��� ����:0.0\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		// ���� ���� �ҷ�����
		try (BufferedReader br = new BufferedReader(new FileReader(specPath))) {
			while ((line = br.readLine()) != null) {
				if(line.equals("�丮��"))
					job = "�丮��";
				else if(line.equals("����"))
					job = "����";
				else if(line.equals("����"))
					mode = "����";
				else if(line.equals("���̽�"))
					mode = "���̽�";
				else {
					if(line.split(":")[0].equals("���õ�")) {
						if(job.equals("�丮��"))
							cookLevel = Integer.parseInt(line.split(":")[1]);
						else
							minerLevel = Integer.parseInt(line.split(":")[1]);
					}
					else {
						if(job.equals("�丮��")) {
							if(mode.equals("����"))
								statueCook.put(line.split(":")[0], Double.parseDouble(line.split(":")[1]));
							else
								diceCook.put(line.split(":")[0], Double.parseDouble(line.split(":")[1]));
						}
						else {
							if(mode.equals("����"))
								statueMiner.put(line.split(":")[0], Double.parseDouble(line.split(":")[1]));
							else
								diceMiner.put(line.split(":")[0], Double.parseDouble(line.split(":")[1]));
						}
					}
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println("cropsMap " + cropsMap);
//		System.out.println("pCropsMap " + pCropsMap);
//		System.out.println("fishMap " + fishMap);
//		System.out.println("pFishMap " + pFishMap);
	}
	
	
	public static InfoList getInstance() {
		if(infoList == null) 
			infoList = new InfoList();
		return infoList;
	}
	
	public HashMap<String, Double> getStatueCook() {
		return statueCook;
	}
	
	public HashMap<String, Double> getDiceCook() {
		return diceCook;
	}
	
	public HashMap<String, Double> getStatueMiner() {
		return statueMiner;
	}
	
	public HashMap<String, Double> getDiceMiner() {
		return diceMiner;
	}
	
	public HashMap<String, Double> getCropsPrice() {
		return cropsMap;
	}
	
	public HashMap<String, Double> getpCropsPrice() {
		return pCropsMap;
	}
	
	public HashMap<String, Double> getFishPrice() {
		return fishMap;
	}
	
	public HashMap<String, Double> getpFishPrice() {
		return pFishMap;
	}
	
	public int getCookLevel() {
		return cookLevel;
	}
	
	public int getMinerLevel() {
		return minerLevel;
	}
	
	public String[] getCrops() {
		return cropsName;
	}
	
	public String[] getpCrops() {
		return pCropsName;
	}
	
	public String[] getFish() {
		return fishName;
	}
	
	public String[] getpFish() {
		return pFishName;
	}
	
	public void setStatueCook(HashMap<String, Double> statueCook) {
		this.statueCook = statueCook;
	}
	
	public void setDiceCook(HashMap<String, Double> diceCook) {
		this.diceCook = diceCook;
	}
	
	public void setStatueMiner(HashMap<String, Double> statueMiner) {
		this.statueMiner = statueMiner;
	}
	
	public void setDiceMiner(HashMap<String, Double> diceMiner) {
		this.diceMiner = diceMiner;
	}
	
	public void setCropsPrice(HashMap<String, Double> hm) {
		cropsMap = hm;
	}
	
	public void setpCropsPrice(HashMap<String, Double> hm) {
		pCropsMap = hm;
	}
	
	public void setFishPrice(HashMap<String, Double> hm) {
		fishMap = hm;
	}
	
	public void setpFishPrice(HashMap<String, Double> hm) {
		pFishMap = hm;
	}
	
	public void setCookLevel(int lv) {
		cookLevel = lv;
	}
	
	public void setMinerLevel(int lv) {
		minerLevel = lv;
	}
	
	// �ü�
	public void savePriceInfo() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(specPath, false))) {
			bw.write("�丮��\n");
			bw.write("���õ�:" + cookLevel + "\n");
			bw.write("����\n");
			bw.write("1��� ����ġ:" + statueCook.get("1��� ����ġ") + "\n");
			bw.write("2��� ����ġ:" + statueCook.get("2��� ����ġ") + "\n");
			bw.write("��� �̼Ҹ� Ȯ��:" + statueCook.get("��� �̼Ҹ� Ȯ��") + "\n");
			bw.write("�丮�� Ż Ȯ��:" + statueCook.get("�丮�� Ż Ȯ��") + "\n");
			bw.write("�丮 �뼺�� Ȯ��:" + statueCook.get("�丮 �뼺�� Ȯ��") + "\n");
			bw.write("���� �ߵ� Ȯ��:"+ statueCook.get("���� �ߵ� Ȯ��") + "\n");
			bw.write("���̽�\n");
			bw.write("1��� ����ġ:" + diceCook.get("1��� ����ġ") + "\n");
			bw.write("2��� ����ġ:" + diceCook.get("2��� ����ġ") + "\n");
			bw.write("�丮�� Ż Ȯ��:" + diceCook.get("�丮�� Ż Ȯ��") + "\n");
			bw.write("����\n");
			bw.write("���õ�:1\n");
			bw.write("����\n");
			bw.write("����� �߰� ȹ��� ����:" + statueMiner.get("����� �߰� ȹ��� ����") + "\n");
			bw.write("��ȭ�� ���� ȹ��� ����:" + statueMiner.get("��ȭ�� ���� ȹ��� ����") + "\n");
			bw.write("������ ���� ȹ��� ����:" + statueMiner.get("������ ���� ȹ��� ����") + "\n");
			bw.write("���̽�\n");
			bw.write("����� �߰� ȹ��� ����:" + diceMiner.get("����� �߰� ȹ��� ����") + "\n");
			bw.write("��ȭ�� ���� ȹ��� ����:" + diceMiner.get("��ȭ�� ���� ȹ��� ����") + "\n");
			bw.write("������ ���� ȹ��� ����:" + diceMiner.get("������ ���� ȹ��� ����") + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ���õ�
	public void saveSpecInfo() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(specPath, false))) {
			bw.write("�丮��\n");
    		bw.write("���õ�:" + cookLevel + "\n");
    		bw.write("����\n");
    		bw.write("1��� ����ġ:" + statueCook.get("1��� ����ġ") + "\n");
    		bw.write("2��� ����ġ:" + statueCook.get("2��� ����ġ") + "\n");
    		bw.write("��� �̼Ҹ� Ȯ��:" + statueCook.get("��� �̼Ҹ� Ȯ��") + "\n");
    		bw.write("�丮�� Ż Ȯ��:" + statueCook.get("�丮�� Ż Ȯ��") + "\n");
    		bw.write("�丮 �뼺�� Ȯ��:" + statueCook.get("�丮 �뼺�� Ȯ��") + "\n");
    		bw.write("���� �ߵ� Ȯ��:"+ statueCook.get("���� �ߵ� Ȯ��") + "\n");
    		bw.write("���̽�\n");
    		bw.write("1��� ����ġ:" + diceCook.get("1��� ����ġ") + "\n");
    		bw.write("2��� ����ġ:" + diceCook.get("2��� ����ġ") + "\n");
    		bw.write("�丮�� Ż Ȯ��:" + diceCook.get("�丮�� Ż Ȯ��") + "\n");
    		bw.write("����\n");
    		bw.write("���õ�:1\n");
    		bw.write("����\n");
    		bw.write("����� �߰� ȹ��� ����:" + statueMiner.get("����� �߰� ȹ��� ����") + "\n");
    		bw.write("��ȭ�� ���� ȹ��� ����:" + statueMiner.get("��ȭ�� ���� ȹ��� ����") + "\n");
    		bw.write("������ ���� ȹ��� ����:" + statueMiner.get("������ ���� ȹ��� ����") + "\n");
    		bw.write("���̽�\n");
    		bw.write("����� �߰� ȹ��� ����:" + diceMiner.get("����� �߰� ȹ��� ����") + "\n");
    		bw.write("��ȭ�� ���� ȹ��� ����:" + diceMiner.get("��ȭ�� ���� ȹ��� ����") + "\n");
    		bw.write("������ ���� ȹ��� ����:" + diceMiner.get("������ ���� ȹ��� ����") + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}


	public String[] getCookStatueName() {
		return cookStatueName;
	}


	public void setCookStatueName(String[] cookStatueName) {
		this.cookStatueName = cookStatueName;
	}


	public String[] getCookdiceName() {
		return cookdiceName;
	}


	public void setCookdiceName(String[] cookdiceName) {
		this.cookdiceName = cookdiceName;
	}


	public String[] getMinerStatueName() {
		return minerStatueName;
	}


	public void setMinerStatueName(String[] minerStatueName) {
		this.minerStatueName = minerStatueName;
	}


	public String[] getMinerDiceName() {
		return minerDiceName;
	}


	public void setMinerDiceName(String[] minerDiceName) {
		this.minerDiceName = minerDiceName;
	}
}
