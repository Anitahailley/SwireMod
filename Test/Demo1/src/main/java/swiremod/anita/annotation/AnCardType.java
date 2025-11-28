package swiremod.anita.annotation;

import com.megacrit.cardcrawl.cards.AbstractCard;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited //可被继承
public @interface AnCardType {
    public AbstractCard.CardType value();
}
