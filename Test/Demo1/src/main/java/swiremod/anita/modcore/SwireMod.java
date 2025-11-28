package swiremod.anita.modcore;

import swiremod.anita.characters.SwireTheElegantWit;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.Keyword;


import java.nio.charset.StandardCharsets;

import static com.megacrit.cardcrawl.core.Settings.language;

@SpireInitializer// 加载mod的注解 让Mod The Spire知道创建了一个mod
public class SwireMod implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber , EditKeywordsSubscriber {
    // 人物选择界面按钮的图片
    private static final String MY_CHARACTER_BUTTON = "anitaResouces/img/char/Character_Button.png";
    // 人物选择界面的立绘
    private static final String MY_CHARACTER_PORTRAIT = "anitaResouces/img/char/Character_Portrait.png";
    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = "anitaResouces/img/512/bg_attack_512.png";
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = "anitaResouces/img/512/bg_power_512.png";
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = "anitaResouces/img/512/bg_skill_512.png";
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = "anitaResouces/img/char/small_orb.png";
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = "anitaResouces/img/1024/bg_attack.png";
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = "anitaResouces/img/1024/bg_power.png";
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = "anitaResouces/img/1024/bg_skill.png";
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = "anitaResouces/img/char/card_orb.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENEYGY_ORB = "anitaResouces/img/char/cost_orb.png";
    //FIXME 没理解干嘛的
    public static final Color SWIREMOD_COLOR = new Color(79.0F / 255.0F, 185.0F / 255.0F, 9.0F / 255.0F, 1.0F);

    public SwireMod() {
        // 告诉basemod你要订阅事件
        /*要想订阅这些事件，首先你要实现相应接口，然后写相应的触发函数，最后告诉basemod你要订阅事件*/
        BaseMod.subscribe(this);
        // 注册颜色
        BaseMod.addColor(SwireTheElegantWit.PlayerColorEnum.SWIRE_GOOD, SWIREMOD_COLOR, SWIREMOD_COLOR, SWIREMOD_COLOR, SWIREMOD_COLOR, SWIREMOD_COLOR, SWIREMOD_COLOR, SWIREMOD_COLOR,BG_ATTACK_512,BG_SKILL_512,BG_POWER_512,ENEYGY_ORB,BG_ATTACK_1024,BG_SKILL_1024,BG_POWER_1024,BIG_ORB,SMALL_ORB);
    }


    // 注解需要调用的方法，必须写
    public static void initialize() {
        new SwireMod();
    }

    //当basemod开始注册mod卡牌时，便会调用这个函数
    @Override
    public void receiveEditCards() {
        //这里写添加卡牌的代码
        // 向basemod注册卡牌
        new AutoAdd("Swire_the_Elegant_Wit") // 自动添加包内所有卡牌
                .packageFilter("anita.pojo.cards")        // 扫描该类包及其内部包
                .setDefaultSeen(true)                                 //卡牌标为可见
                .cards();                                             //添加包内的卡牌

    }
    // 当开始添加人物时，调用这个方法
    @Override
    public void receiveEditCharacters() {
        //FIXME 向basemod注册人物
//        BaseMod.addCharacter(new SwireTheElegantWit(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, CHARACTER_SWIRE);
    }
    // 当开始添加关键词时，调用这个方法
    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        //默认为中文
        String lang = "zhs";
        /*if (language == Settings.GameLanguage.ENG) {
            lang = "eng";
        }*/
        String pathKeyWords = new StringBuilder("anitaResouces/localization/Keywords_").append(lang).append(".json").toString();
        String json = Gdx.files.internal(pathKeyWords)
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                // 这个id要全小写
                BaseMod.addKeyword("swire_the_elegant_wit", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveEditStrings() {
        String lang;
        StringBuilder sBuilder = new StringBuilder();
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS"; // 如果语言设置为简体中文，则加载ZHS文件夹的资源
        } else {
            lang = "ZHS";
//            lang = "ENG"; // 如果没有相应语言的版本，默认加载英语
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, sBuilder.append("anitaResouces/localization/") .append(lang).append("/cards.json").toString()); // 加载相应语言的卡牌本地化内容。
        // 如果是中文，加载的就是"anitaResouces/localization/ZHS/cards.json"
        sBuilder.setLength(0);
        BaseMod.loadCustomStringsFile(CharacterStrings.class, sBuilder.append("anitaResouces/localization/") .append(lang).append("/characters.json").toString());
    }
}
