package swiremod.anita.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;


public class XStrikeAction extends AbstractGameAction {

    private boolean freeToPlayOnce;
    private DamageInfo.DamageType damageType;
    private AbstractPlayer p;
    private int energyOnUse = -1;
    private int damage;
    private AbstractMonster m;

    public XStrikeAction(AbstractPlayer p, AbstractMonster m, int damage, DamageInfo.DamageType damageType, boolean freeToPlayOnce, int energyOnUse) {
        this.damage = damage;
        this.m = m;
        //设置伤害类型
        this.damageType = damageType;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        //设置持续时间
        this.duration = Settings.ACTION_DUR_XFAST;
        //设置动作类型
        this.actionType = ActionType.DAMAGE;

        this.energyOnUse = energyOnUse;
    }

    @Override
    public void update() {
        // 获取当前能量数
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        // 如果玩家有“化学性”遗物，则增加2次
        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (effect > 0) {
            // 播放音效和特效
            this.addToTop(new SFXAction("ATTACK_WHIRLWIND"));
            this.addToTop(new VFXAction(new WhirlwindEffect(), 0.0F));

            // 根据能量数执行多次攻击
            for (int i = 0; i < effect; i++) {
                // 播放音效和特效
                this.addToTop(new SFXAction("ATTACK_HEAVY"));
                // 播放特效
                this.addToTop(new VFXAction(this.p, new CleaveEffect(), 0.0F));

                // 使用DamageAction实现单体攻击
                // AttackEffect.NONE 表示敌人受击的效果为 无特效
                this.addToTop(new DamageAction(this.m, new DamageInfo(this.p, this.damage, this.damageType), AttackEffect.NONE));
            }

            // 消耗所有能量

            if (!this.freeToPlayOnce) {
                //
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}
