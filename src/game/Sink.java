package game;

import ui.Options;
import ui.UButton;
import ui.ULabel;

import java.awt.*;
import java.util.ArrayList;

public class Sink extends Room {
    public Sink() {
        super("Sink", "you can tell the water hasn't been run at all today.");
        this.buttonNames = new String[]{"back"};
        this.buttons = new ArrayList<>();
    }

    @Override
    public void update(boolean type) {
        super.update(false);

        this.prompt = new ULabel(this.promptString);
        this.prompt.setFont(new Font("FANTASY MAGIST", Font.ITALIC, 32));
        this.prompt.setForeground(new Color(205, 205, 205));

        this.prompt.setSize(this.prompt.getPreferredSize());
        this.prompt.setLocation(
                (scene.getWindow().getWidth() / 2) - this.prompt.getWidth() / 2,
                (int) (scene.getWindow().getHeight() * .3));

        this.prompt.set();
        this.prompt.update();
        this.scene.getSurface().add(this.prompt);

        if (type) {
            this.prompt.typewriter(null, 50, "mono", () -> {
                scene.rosterButton.active = true;
                scene.getSurface().add(scene.rosterButton, 2);

                scene.inventoryButton.active = true;
                scene.getSurface().add(scene.inventoryButton, 2);

                this.displayButtons();
                this.activateButtons();

                UButton[] array = new UButton[this.buttons.size()];
                array = this.buttons.toArray(array);
                Options.displayOptions(
                        0,
                        padding,
                        (int) (this.scene.getWindow().getHeight() * .4),
                        this.scene,
                        array
                );
            });
        } else {
            this.displayButtons();

            UButton[] array = new UButton[this.buttons.size()];
            array = this.buttons.toArray(array);
            Options.displayOptions(
                    0,
                    padding,
                    (int) (this.scene.getWindow().getHeight() * .4),
                    this.scene,
                    array);
        }
    }

    @Override
    public void a() {
        this.deactivateButtons();
        this.transitionReverse(Room.getRoom("Bathroom", this.player, this.scene));
    }
}