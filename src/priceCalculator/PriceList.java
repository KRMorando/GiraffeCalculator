package priceCalculator;

public class PriceList {
	// 일반 농작물			10종류
	static String[] cropsName = { "토마토", "포도", "옥수수", "마늘", "고추", "홉", "가지", "양배추", "배추", "파인애플"};
	static int[] cropsPrice = new int[cropsName.length];
	
	// 숙련도 농작물	15종류
	static String[] pCropsName = { "카람볼라", "블루베리", "구기자", "망고", "오이", "파파야", "체리", "블랙베리", "코코넛", "리치", "두리안", "키위", "아보카도", "라즈베리", "구아바" };
	static int[] pCropsPrice = new int[pCropsName.length];
	
	// 일반 물고기			13종류
	static String[] fishName = { "메기", "잡어", "금붕어", "문어", "강꼬치", "정어리", "숭어", "다랑어", "연어", "개복치", "잉어", "농어", "적색퉁돔"};
	static int[] fishPrice = new int[fishName.length];
	
	// 숙련도 물고기	9종류
	static String[] pFishName = {"블루탱", "해파리", "랍스터", "아귀", "줄돔", "가오리", "흰동가리", "뱀장어", "개구리" };
	static int[] pfishPrice = new int[pFishName.length];
}