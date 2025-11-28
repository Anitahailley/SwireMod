package swiremod.anita.pojo.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import swiremod.anita.pojo.cards.parents.SkillBase;
import swiremod.anita.utils.CardCreatUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Draw extends SkillBase {

    static {
        CardCreatUtils.initCardInfo(Draw.class);
    }

    public Draw() {
        //target 是技能的目标，NONE表示技能不攻击
        super(Draw.class, CardCreatUtils.id, CardCreatUtils.name, 1, CardCreatUtils.description, CardRarity.COMMON, CardTarget.NONE, true);
        this.magicNumber=this.baseMagicNumber=1;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon .actionManager.addToBottom(new DrawCardAction(p,magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }
}
