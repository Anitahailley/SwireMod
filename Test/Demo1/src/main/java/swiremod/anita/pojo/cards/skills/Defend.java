package swiremod.anita.pojo.cards.skills;

import swiremod.anita.pojo.cards.parents.SkillBase;
import swiremod.anita.utils.CardCreatUtils;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Defend extends SkillBase {
    public static final String ID = CardCreatUtils.getCardId(Defend.class);
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public Defend() {
        super(Defend.class,ID, NAME,1, DESCRIPTION,CardRarity.BASIC, CardTarget.SELF, true);
        this.block=this.baseBlock=5;
        this.tags.add(CardTags.STARTER_DEFEND);
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
            this.upgradeBlock(4);//将该卡牌的格挡值提高4点。
            //该方法内部将卡牌标注为升级过的卡牌

        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //获得格挡
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    }
}
