package swiremod.anita.pojo.cards.parents;

import swiremod.anita.annotation.AnCardType;
import swiremod.anita.utils.CardCreatUtils;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;


@AnCardType(CardType.POWER)
public abstract class AbilityBase extends CardBase {
    public AbilityBase(Class<? extends CardBase> cardClass, String id, String name, int cost, String rawDescription, CardRarity rarity, CardTarget target, boolean isTestImg) {
        super(cardClass, id, name, isTestImg ? CardCreatUtils.getImgPath("test_img") : CardCreatUtils.getImgPath(cardClass), cost, rawDescription, rarity, target);
    }
}
