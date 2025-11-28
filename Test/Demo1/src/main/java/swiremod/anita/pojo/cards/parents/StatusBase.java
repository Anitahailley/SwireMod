package swiremod.anita.pojo.cards.parents;

import swiremod.anita.annotation.AnCardType;
import swiremod.anita.utils.CardCreatUtils;

import com.megacrit.cardcrawl.cards.AbstractCard.CardType;


@AnCardType(CardType.STATUS)
public abstract class StatusBase extends CardBase {
    public StatusBase(Class<? extends CardBase> aclass, String id, String name, int cost, String rawDescription, CardRarity rarity, CardTarget target, boolean isTestImg) {
        super(aclass, id, name, isTestImg ? CardCreatUtils.getImgPath("test_img") : CardCreatUtils.getImgPath(aclass), cost, rawDescription, rarity, target);
    }
}
