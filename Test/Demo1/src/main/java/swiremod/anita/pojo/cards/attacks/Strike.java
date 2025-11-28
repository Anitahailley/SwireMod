package swiremod.anita.pojo.cards.attacks;

import swiremod.anita.annotation.AnCardType;
import swiremod.anita.utils.CardCreatUtils;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;

@AnCardType(CardType.ATTACK)
//继承 CustomCard 识别为卡牌
public class Strike extends CustomCard{
    //卡牌ID 要绝对唯一 不能和别的mod的卡牌ID重复
    public static final String ID = CardCreatUtils.getCardId(Strike.class);
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    //显示在卡牌上方的卡牌名字
    //private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    //卡牌卡图的相对路径
    private static final String IMG_PATH = CardCreatUtils.getImgPath(Strike.class);
    //卡牌的费用 -1:X费  -2费不显示能量图标（如诅咒卡状态卡等）
    private static final int COST = 1;
    //卡牌的描述   !D!（前后空格）将被伤害数值替代
    //private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    //卡牌类型（攻击牌、技能牌、能力牌、诅咒牌、状态牌）
    private static final CardType TYPE = CardType.ATTACK;
    //卡牌颜色，比如原版的红、绿、蓝、紫、无色，诅咒
    //TODO 需要创建一个属于该角色的卡牌颜色
    private static final CardColor COLOR = CardColor.COLORLESS;
    //卡牌稀有度
    /*  BASIC：基础稀有度
        SPECIAL：特殊稀有度
        COMMON：普通稀有度
        UNCOMMON：罕见稀有度
        RARE：稀有稀有度
        CURSE：诅咒稀有度*/
    private static final CardRarity RARITY = CardRarity.BASIC;
    //卡牌指向类型的目标。实际功能只有是否指向敌人的区分
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Strike() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 6;//baseDamage是卡牌的基础伤害数值，也就是没有计算易伤等之前的伤害
        /*tags是卡牌的标签，例如添加STARTER_STRIKE（基础打击）让潘多拉变化这张牌，
        添加STRIKE（打击）让完美打击计算这张牌。注意添加了STARTER_STRIKE并不会视为添加了STRIKE*/
        this.tags.add(CardTags.STARTER_STRIKE);
        this.tags.add(CardTags.STRIKE);
    }

    /**
     * 升级卡牌时调用的方法
     */
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
            this.upgradeDamage(3); // 将该卡牌的伤害提高3点。

            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    /**
     * 当卡牌被使用时，调用这个方法
     *
     * @param p 玩家实体类
     * @param m 指向的怪物类。（无指向时为null，包括攻击所有敌人时）
     */
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        /*这行代码的意思是调用AbstractDungeon的变量actionManager的方法addToBottom。
AbstractDungeon是一个拥有游戏大部分代码的类（游戏作者矢野偷懒把东西都写在这里了）。
actionManager是游戏的事件队列管理器。
addToBottom将你输入的参数添加到事件队列的末尾。*/
        AbstractDungeon.actionManager.addToBottom(
                /*DamageAction 造成伤害的事件。该事件的构造函数有许多重载，这里使用两个参数的该重载。
                    target是该事件造成伤害的目标，比如你指向的怪物。
                    info是伤害信息。*/
                new DamageAction(
                        m,

                        new DamageInfo(
                                p,
                                damage,
                                DamageType.NORMAL
                        )
                        /*描述伤害的信息。
                        source是该伤害的来源（比如说玩家）。
                        base是该伤害的数值（关于伤害计算详见另外章节）。
                        type是伤害类型。攻击伤害使用NORMAL，非攻击伤害（荆棘等）使用THORNS，失去生命使用HP_LOSS。*/
                )
        );

    }


}
