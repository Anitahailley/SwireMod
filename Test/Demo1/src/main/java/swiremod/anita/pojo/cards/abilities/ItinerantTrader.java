package swiremod.anita.pojo.cards.abilities;

import swiremod.anita.abilities.Guineas;
import swiremod.anita.pojo.cards.parents.AbilityBase;
import swiremod.anita.pojo.cards.skills.Defend;
import swiremod.anita.utils.CardCreatUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ItinerantTrader extends AbilityBase {
    public static final String ID = CardCreatUtils.getCardId(ItinerantTrader.class);
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;

    public ItinerantTrader() {
        super(Defend.class, ID, NAME, 1, DESCRIPTION, CardRarity.BASIC, CardTarget.SELF, true);
        this.tags.add(CardTags.EMPTY);
        this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        this.upgradeName();
        //升级变为固有
        this.isInnate = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 使用时 每消耗一个能量就增加一枚金币，即增加一层guineas能力
        int energyConsumed = this.costForTurn;
        if (energyConsumed > 0) {
            //stackAmount energyConsumed 能力的叠加数
            addToBot(new ApplyPowerAction(p, p, new Guineas(p,0), energyConsumed));
        }

    }
}
