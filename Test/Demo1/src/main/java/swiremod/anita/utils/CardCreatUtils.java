package swiremod.anita.utils;

import swiremod.anita.annotation.AnCardType;

import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class CardCreatUtils {
    public static String id;
    public static CardStrings cardStrings;
    public static String name;
    public static String description;

    private CardCreatUtils() {}

    /**
     * 快速生成卡牌的id 即 [modId]:[卡牌类名]
     *
     * @param aclass 卡牌类的字节码
     * @return 卡牌id
     */
    public static final String getCardId(Class<?> aclass) {
        return new StringBuilder("Swire_the_Elegant_Wit:")
                .append(aclass.getSimpleName())
                .toString();
    }
    public static final String getCardId(String str) {
        return new StringBuilder("Swire_the_Elegant_Wit:")
                .append(str)
                .toString();
    }

    /**
     * 快速生成卡牌的图片路径
     *
     * @param aclass 卡牌类的字节码
     * @return 卡牌图片路径
     */
    public static final String getImgPath(Class<? extends CustomCard> aclass) {
        //通过反射获取的类的注解上的value值来获取图片路径
        AnCardType anno = aclass.getAnnotation(AnCardType.class);
        String packegeName;
        AbstractCard.CardType type = anno.value();
        if (type.equals(CardType.ATTACK))
            packegeName = "attackCards/";
        else if (type.equals(CardType.SKILL))
            packegeName = "skillCards/";
        else if (type.equals(CardType.POWER))
            packegeName = "abilityCards/";
        else if (type.equals(CardType.CURSE))
            packegeName = "curseCards/";
        else
            packegeName = "statusCards/";
        return new StringBuilder("anitaResouces/img/cards/")
                .append(packegeName)
                .append(aclass.getSimpleName())//反射获取到卡牌类名
                .append(".png")
                .toString();
    }
    /**
     * 快速生成测试卡牌的图片路径
     *
     * @param testCardName 测试卡牌的图片名（没有后缀）
     * @return 测试卡牌图片路径
     */
    public static final String getImgPath(String testCardName) {
        return new StringBuilder("anitaResouces/img/cards/testCards/")
                .append(testCardName)
                .append(".png")
                .toString();
    }

    /**
     * 初始化卡牌信息
     * @param clazz 卡牌类的字节码
     */
    public static void initCardInfo(Class<?> clazz) {
        id = getCardId(clazz);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
        name = cardStrings.NAME;
        description = cardStrings.DESCRIPTION;
    }
}
