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

	// 폴더 경로
	private String folderPath = "C:/GiraffeCalculator";
	private String pricePath = folderPath + "/price.txt";
	private String specPath = folderPath + "/specification.txt";
	private File folder = new File(folderPath);
	private File price = new File(pricePath);
	private File spec = new File(specPath);
	
	// 최초 생성 여부
	boolean isPriceNew = false;
	boolean isSpecNew = false;
		
	// 일반 농작물		10종류
	private String[] cropsName = { "토마토", "포도", "옥수수", "마늘", "고추", "홉", "가지", "양배추", "배추", "파인애플"};
	private HashMap<String, Double> cropsMap = new HashMap<>();
		
	// 숙련도 농작물	15종류
	private String[] pCropsName = { "카람볼라", "블루베리", "구기자", "망고", "오이", "파파야", "체리", "블랙베리", "코코넛", "리치", "두리안", "키위", "아보카도", "라즈베리", "구아바" };
	private HashMap<String, Double> pCropsMap = new HashMap<>();
		
	// 일반 물고기		13종류
	private String[] fishName = { "메기", "잡어", "금붕어", "문어", "강꼬치", "정어리", "숭어", "다랑어", "연어", "개복치", "잉어", "농어", "적색퉁돔"};
	private HashMap<String, Double> fishMap = new HashMap<>();
		
	// 숙련도 물고기	9종류
	private String[] pFishName = {"블루탱", "해파리", "랍스터", "아귀", "줄돔", "가오리", "흰동가리", "뱀장어", "개구리" };
	private HashMap<String, Double> pFishMap = new HashMap<>();
	
	// 요리사
	// 1등급 가중치, 2등급 가중치, 3등급 가중치, 재료 미소모 확률, 요리가 탈 확률, 요리 대성공 확률, 은혜 발동 확률
	private int cookLevel = 1;
	// 동상, 다이스
	private String[] cookStatueName = { "1등급 가중치", "2등급 가중치", "재료 미소모 확률", "요리가 탈 확률", "요리 대성공 확률", "은혜 발동 확률" };
	private String[] cookdiceName = { "1등급 가중치", "2등급 가중치", "요리가 탈 확률" };
	private HashMap<String, Double> statueCook = new HashMap<>();
	private HashMap<String, Double> diceCook = new HashMap<>();
	
	// 광부
	// 결과물 추가 획득률 증가, 강화된 광물 획득률 증가, 순수한 광물 획득률 증가
	private int minerLevel = 1;
	// 동상, 다이스
	private String[] minerStatueName = { "결과물 추가 획득률 증가" , "강화된 광물 획득률 증가" , "순수한 광물 획득률 증가"};
	private String[] minerDiceName = { "결과물 추가 획득률 증가" , "강화된 광물 획득률 증가" , "순수한 광물 획득률 증가"};
	private HashMap<String, Double> statueMiner = new HashMap<>();
	private HashMap<String, Double> diceMiner = new HashMap<>();
		
	private InfoList() {
		try {
			// 폴더 및 파일이 존재하지 않을시 생성
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
				bw.write("일반 농작물\n");
				for(int i = 0; i < cropsName.length;i++)
					bw.write(cropsName[i] + ":0\n");
				bw.write("숙련도 농작물\n");
				for(int i = 0; i < pCropsName.length;i++)
					bw.write(pCropsName[i] + ":0\n");
				bw.write("일반 물고기\n");
				for(int i = 0; i < fishName.length;i++)
					bw.write(fishName[i] + ":0\n");
				bw.write("숙련도 물고기\n");
				for(int i = 0; i < pFishName.length;i++)
					bw.write(pFishName[i] + ":0\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 농작물, 물고기 시세 불러오기
		try (BufferedReader br = new BufferedReader(new FileReader(pricePath))) {
			while ((line = br.readLine()) != null) {
				if(line.equals("일반 농작물"))
					mode = "일반 농작물";
				else if(line.equals("숙련도 농작물"))
					mode = "숙련도 농작물";
				else if(line.equals("일반 물고기"))
					mode = "일반 물고기";
				else if(line.equals("숙련도 물고기"))
					mode = "숙련도 물고기";
				else {
					if(mode.equals("일반 농작물"))
						cropsMap.put(line.split(":")[0], Double.parseDouble(line.split(":")[1]));
					if(mode.equals("숙련도 농작물"))
						pCropsMap.put(line.split(":")[0], Double.parseDouble(line.split(":")[1]));
					if(mode.equals("일반 물고기"))
						fishMap.put(line.split(":")[0], Double.parseDouble(line.split(":")[1]));
					if(mode.equals("숙련도 물고기"))
						pFishMap.put(line.split(":")[0], Double.parseDouble(line.split(":")[1]));
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		if(isSpecNew) {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(specPath))) {
				bw.write("요리사\n");
				bw.write("숙련도:1\n");
				bw.write("동상\n");
				bw.write("1등급 가중치:0.0\n");
				bw.write("2등급 가중치:0.0\n");
				bw.write("재료 미소모 확률:0.0\n");
				bw.write("요리가 탈 확률:0.0\n");
				bw.write("요리 대성공 확률:0.0\n");
				bw.write("은혜 발동 확률:0.0\n");
				bw.write("다이스\n");
				bw.write("1등급 가중치:0.0\n");
				bw.write("2등급 가중치:0.0\n");
				bw.write("요리가 탈 확률:0.0\n");
				bw.write("광부\n");
				bw.write("숙련도:1\n");
				bw.write("결과물 추가 획득률 증가:0.0\n");
				bw.write("강화된 광물 획득률 증가:0.0\n");
				bw.write("순수한 광물 획득률 증가:0.0\n");
				bw.write("동상\n");
				bw.write("결과물 추가 획득률 증가:0.0\n");
				bw.write("강화된 광물 획득률 증가:0.0\n");
				bw.write("순수한 광물 획득률 증가:0.0\n");
				bw.write("다이스\n");
				bw.write("결과물 추가 획득률 증가:0.0\n");
				bw.write("강화된 광물 획득률 증가:0.0\n");
				bw.write("순수한 광물 획득률 증가:0.0\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		// 직업 스펙 불러오기
		try (BufferedReader br = new BufferedReader(new FileReader(specPath))) {
			while ((line = br.readLine()) != null) {
				if(line.equals("요리사"))
					job = "요리사";
				else if(line.equals("광부"))
					job = "광부";
				else if(line.equals("동상"))
					mode = "동상";
				else if(line.equals("다이스"))
					mode = "다이스";
				else {
					if(line.split(":")[0].equals("숙련도")) {
						if(job.equals("요리사"))
							cookLevel = Integer.parseInt(line.split(":")[1]);
						else
							minerLevel = Integer.parseInt(line.split(":")[1]);
					}
					else {
						if(job.equals("요리사")) {
							if(mode.equals("동상"))
								statueCook.put(line.split(":")[0], Double.parseDouble(line.split(":")[1]));
							else
								diceCook.put(line.split(":")[0], Double.parseDouble(line.split(":")[1]));
						}
						else {
							if(mode.equals("동상"))
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
	
	// 시세
	public void savePriceInfo() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(specPath, false))) {
			bw.write("요리사\n");
			bw.write("숙련도:" + cookLevel + "\n");
			bw.write("동상\n");
			bw.write("1등급 가중치:" + statueCook.get("1등급 가중치") + "\n");
			bw.write("2등급 가중치:" + statueCook.get("2등급 가중치") + "\n");
			bw.write("재료 미소모 확률:" + statueCook.get("재료 미소모 확률") + "\n");
			bw.write("요리가 탈 확률:" + statueCook.get("요리가 탈 확률") + "\n");
			bw.write("요리 대성공 확률:" + statueCook.get("요리 대성공 확률") + "\n");
			bw.write("은혜 발동 확률:"+ statueCook.get("은혜 발동 확률") + "\n");
			bw.write("다이스\n");
			bw.write("1등급 가중치:" + diceCook.get("1등급 가중치") + "\n");
			bw.write("2등급 가중치:" + diceCook.get("2등급 가중치") + "\n");
			bw.write("요리가 탈 확률:" + diceCook.get("요리가 탈 확률") + "\n");
			bw.write("광부\n");
			bw.write("숙련도:1\n");
			bw.write("동상\n");
			bw.write("결과물 추가 획득률 증가:" + statueMiner.get("결과물 추가 획득률 증가") + "\n");
			bw.write("강화된 광물 획득률 증가:" + statueMiner.get("강화된 광물 획득률 증가") + "\n");
			bw.write("순수한 광물 획득률 증가:" + statueMiner.get("순수한 광물 획득률 증가") + "\n");
			bw.write("다이스\n");
			bw.write("결과물 추가 획득률 증가:" + diceMiner.get("결과물 추가 획득률 증가") + "\n");
			bw.write("강화된 광물 획득률 증가:" + diceMiner.get("강화된 광물 획득률 증가") + "\n");
			bw.write("순수한 광물 획득률 증가:" + diceMiner.get("순수한 광물 획득률 증가") + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 숙련도
	public void saveSpecInfo() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(specPath, false))) {
			bw.write("요리사\n");
    		bw.write("숙련도:" + cookLevel + "\n");
    		bw.write("동상\n");
    		bw.write("1등급 가중치:" + statueCook.get("1등급 가중치") + "\n");
    		bw.write("2등급 가중치:" + statueCook.get("2등급 가중치") + "\n");
    		bw.write("재료 미소모 확률:" + statueCook.get("재료 미소모 확률") + "\n");
    		bw.write("요리가 탈 확률:" + statueCook.get("요리가 탈 확률") + "\n");
    		bw.write("요리 대성공 확률:" + statueCook.get("요리 대성공 확률") + "\n");
    		bw.write("은혜 발동 확률:"+ statueCook.get("은혜 발동 확률") + "\n");
    		bw.write("다이스\n");
    		bw.write("1등급 가중치:" + diceCook.get("1등급 가중치") + "\n");
    		bw.write("2등급 가중치:" + diceCook.get("2등급 가중치") + "\n");
    		bw.write("요리가 탈 확률:" + diceCook.get("요리가 탈 확률") + "\n");
    		bw.write("광부\n");
    		bw.write("숙련도:1\n");
    		bw.write("동상\n");
    		bw.write("결과물 추가 획득률 증가:" + statueMiner.get("결과물 추가 획득률 증가") + "\n");
    		bw.write("강화된 광물 획득률 증가:" + statueMiner.get("강화된 광물 획득률 증가") + "\n");
    		bw.write("순수한 광물 획득률 증가:" + statueMiner.get("순수한 광물 획득률 증가") + "\n");
    		bw.write("다이스\n");
    		bw.write("결과물 추가 획득률 증가:" + diceMiner.get("결과물 추가 획득률 증가") + "\n");
    		bw.write("강화된 광물 획득률 증가:" + diceMiner.get("강화된 광물 획득률 증가") + "\n");
    		bw.write("순수한 광물 획득률 증가:" + diceMiner.get("순수한 광물 획득률 증가") + "\n");
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
