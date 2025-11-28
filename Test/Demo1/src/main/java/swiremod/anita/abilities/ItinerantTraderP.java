package swiremod.anita.abilities;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import swiremod.anita.utils.CardCreatUtils;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ItinerantTraderP extends AbstractPower{ // 能力的ID
    public static final String POWER_ID = CardCreatUtils.getCardId(ItinerantTraderP.class);
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ItinerantTraderP(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;


        this.amount = -1;

        // 添加一大一小两张能力图
        String path128 = "anitaResouces/img/abilities/Example84.png";
        String path48 = "anitaResouces/img/abilities/Example32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
    }

  /*  public void onUseCard(AbstractCard card, UseCardAction action) {
        if (Settings.FAST_MODE) {
            // 执行快速模式下的代码
            // 例如跳过动画、立即执行效果等
            this.addToBot(new Guineas(AbstractDungeon.player, this.amount));
        } else {
            // 执行正常模式下的代码
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.amount));
        }

        this.flash();
    }*/

    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount); // 这样，%d就被替换成能力的层数
    }
}
