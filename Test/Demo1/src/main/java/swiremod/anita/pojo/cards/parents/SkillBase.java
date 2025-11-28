package swiremod.anita.pojo.cards.parents;

import swiremod.anita.annotation.AnCardType;
import swiremod.anita.utils.CardCreatUtils;

import com.megacrit.cardcrawl.cards.AbstractCard.CardType;


@AnCardType(CardType.SKILL)
public abstract class SkillBase extends CardBase {
    public SkillBase(Class<? extends CardBase> aClass, String id, String name, int cost, String rawDescription, CardRarity rarity, CardTarget target, boolean isTestImg) {
        super(aClass, id, name, isTestImg ? CardCreatUtils.getImgPath("test_img") : CardCreatUtils.getImgPath(aClass), cost, rawDescription, rarity, target);
    }
}
