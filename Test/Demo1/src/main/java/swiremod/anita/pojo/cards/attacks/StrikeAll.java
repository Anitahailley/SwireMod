package swiremod.anita.pojo.cards.attacks;

import swiremod.anita.pojo.cards.parents.AttackBase;
import swiremod.anita.utils.CardCreatUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//TODO 特点：攻击所有敌人 单次
public class StrikeAll extends AttackBase {
    static {
        CardCreatUtils.initCardInfo(StrikeAll.class);
    }

    public StrikeAll() {
        super(StrikeAll.class, CardCreatUtils.id, CardCreatUtils.name, 1, CardCreatUtils.description, CardRarity.COMMON, CardTarget.ALL_ENEMY, true);
         this.damage =this.baseDamage = 7;
        this.tags.add(CardTags.STRIKE);
        this.multiDamage = new int[]{7, 7, 7, 7, 7};
    }

    @Override
    public void upgrade() {
        this.upgradeName();
        this.upgradeDamage(3);

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        //DamageAllEnemiesAction会遍历当前战斗中的所有敌人，并根据multiDamage数组中的值对每个敌人造成相应伤害。
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(abstractPlayer, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
}
