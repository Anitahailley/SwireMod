package swiremod.anita.pojo.cards.parents;

import swiremod.anita.annotation.AnCardType;
import swiremod.anita.utils.CardCreatUtils;

import com.megacrit.cardcrawl.cards.AbstractCard.CardType;


//减少了注解的使用 ,让注解继承
@AnCardType(CardType.ATTACK)
public abstract class AttackBase extends CardBase{
    public AttackBase(Class<? extends AttackBase> aClass, String id, String name, int cost, String rawDescription, CardRarity rarity, CardTarget target,Boolean isTestImg) {
        //获取了图片路径并传参
        super(aClass,id, name,isTestImg? CardCreatUtils.getImgPath("test_img"):CardCreatUtils.getImgPath(aClass), cost, rawDescription, rarity, target);
    }
}
