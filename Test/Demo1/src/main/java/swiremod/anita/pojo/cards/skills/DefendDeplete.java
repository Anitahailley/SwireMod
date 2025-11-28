package swiremod.anita.pojo.cards.skills;

import swiremod.anita.pojo.cards.attacks.StrikeAll;
import swiremod.anita.pojo.cards.parents.SkillBase;
import swiremod.anita.utils.CardCreatUtils;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//TODO 特点: 消耗 防御
public class DefendDeplete extends SkillBase {
    static {
        CardCreatUtils.initCardInfo(DefendDeplete.class);
    }

    public DefendDeplete() {
        super(StrikeAll.class, CardCreatUtils.id, CardCreatUtils.name, 1, CardCreatUtils.description, CardRarity.UNCOMMON, CardTarget.SELF, true);
        this.block = this.baseBlock = 7;
        //消耗
        this.exhaust = true;
        this.tags.add(CardTags.EMPTY);

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    }
}
