package net.gah.hacks;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class gahHacksScreen extends Screen{

    private final Screen parent;
    private final GameOptions settings;

    public gahHacksScreen(Screen parent, GameOptions gameOptions) {
        super(Text.of("GAH hacks mod"));
        this.parent = parent;
        this.settings = gameOptions;
    }

    Text autoFishingText() {
        if(gahHacksMod.autoFishingEnabled)
            return Text.of("AutoFishing is enabled");
        else
            return Text.of("AutoFishing is disabled");
    }

    protected void init() {
        // AutoFishing Button
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100,
                this.height / 6 + 90, 200, 20, autoFishingText(),
                (button) -> {
                    gahHacksMod.autoFishingEnabled = !gahHacksMod.autoFishingEnabled;
                    button.setMessage(autoFishingText());
                    gahHacksMod.LOGGER.info("Clicked enable autofish");
                }));
        // Back button
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100,
                this.height / 6 + 138, 200, 20, ScreenTexts.BACK,
                (button) -> {
                    this.client.setScreen(this.parent);
                }));
    }
}
