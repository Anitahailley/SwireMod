package swiremod.anita.pojo.cards.attacks;

import swiremod.anita.action.XStrikeAction;
import swiremod.anita.pojo.cards.parents.AttackBase;
import swiremod.anita.utils.CardCreatUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//TODO 特点：X费 打单 对敌
public class XStrike extends AttackBase {


    public static final String ID = CardCreatUtils.getCardId(XStrike.class);
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述

    public XStrike() {//罕见 X费
        super(XStrike.class, ID,NAME,-1, DESCRIPTION,CardRarity.UNCOMMON, CardTarget.ENEMY, true);
        this.tags.add(CardTags.EMPTY);//标签为 无标签
        this.damage=this.baseDamage = 6;
        //标识这张卡牌具有打全体敌方伤害的特性
        this.isMultiDamage = false;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
            this.upgradeDamage(3); // 将该卡牌的伤害提高3点。
        }
    }

    @Override/*
            damageType - 卡牌的基础伤害类型，通常是固定的
            damageTypeForTurn - 当前回合的伤害类型，*/
    public void use(AbstractPlayer p, AbstractMonster m) {
        /*  NORMAL - 普通伤害类型，大多数攻击卡牌使用此类型
            THORNS - 荆棘伤害类型，通常由荆棘遗物或其他防御性能力造成
            HP_LOSS - 生命值损失类型，不被视为攻击伤害，通常用于纯损失HP的效果*/
        AbstractDungeon.actionManager.addToBottom(new XStrikeAction(p, m,this.damage, this.damageTypeForTurn, this.freeToPlayOnce,this.energyOnUse));
        //freeToPlayOnce 表示是否可以免费使用
        //energyOnUse 表示消耗的能量 指定卡牌使用时应该消耗的能量数量
    }
}
