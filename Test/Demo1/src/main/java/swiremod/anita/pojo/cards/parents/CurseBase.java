package swiremod.anita.pojo.cards.parents;

import swiremod.anita.annotation.AnCardType;
import swiremod.anita.utils.CardCreatUtils;

import com.megacrit.cardcrawl.cards.AbstractCard.CardType;


@AnCardType(CardType.CURSE)
public abstract class CurseBase extends CardBase {
    public CurseBase(Class<? extends CardBase> cardClass, String id, String name, String imgPath, int cost, String rawDescription, CardRarity rarity, CardTarget target, boolean isTestImg) {
        super(cardClass, id, name, isTestImg ? CardCreatUtils.getImgPath("test_img") : CardCreatUtils.getImgPath(cardClass), cost, rawDescription, rarity, target);
    }
}
