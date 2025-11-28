package swiremod.anita.pojo.cards.parents;

import swiremod.anita.annotation.AnCardType;
import basemod.abstracts.CustomCard;


public abstract class CardBase extends CustomCard {
    //子类只需要传入 类的字节码，卡牌名称，费用，描述，稀有度，指向目标
    //减少了4个，也就是基本一般的参数参数
    public CardBase(Class<? extends CardBase> cardClass, String id, String name, String imgPath, int cost, String rawDescription,
                    CardRarity rarity, CardTarget target) {
        super(
                id,
                name,
                imgPath,
                cost,
                rawDescription,
                cardClass.getAnnotation(AnCardType.class).value(),
                CardColor.COLORLESS,//FIXME 这里需要创建颜色 以及能量图片等 SWIRE_GOOD
                rarity,
                target
        );
    }
}