package de.macbury;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.*;

public class TestCollapsible extends VisWindow {

    public TestCollapsible () {
        super("collapsible widget");

        columnDefaults(0).left();

        addCloseButton();
        closeOnEscape();

        VisCheckBox collapseCheckBox = new VisCheckBox("show advanced settings");
        collapseCheckBox.setChecked(true);

        VisTable table = new VisTable();
        final CollapsibleWidget collapsibleWidget = new CollapsibleWidget(table);

        VisTable numberTable = new VisTable(true);
        numberTable.add(new VisLabel("2 + 2 * 2 = "));
        numberTable.add(new VisTextField());

        table.defaults().left();
        table.defaults().padLeft(10);
        table.add(new VisCheckBox("advanced option #1")).row();
        table.add(new VisCheckBox("advanced option #2")).row();
        table.add(numberTable).padTop(3).row();

        collapseCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                collapsibleWidget.setCollapsed(!collapsibleWidget.isCollapsed());
            }
        });

        top();
        add(collapseCheckBox).row();
        add(collapsibleWidget).expandX().fillX().row();

        centerWindow();
        pack();
    }
}