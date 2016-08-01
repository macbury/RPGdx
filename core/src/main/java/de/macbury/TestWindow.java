package de.macbury;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.*;
import com.kotcrab.vis.ui.widget.color.ColorPicker;
import com.kotcrab.vis.ui.widget.color.ColorPickerAdapter;
import com.kotcrab.vis.ui.widget.spinner.ArraySpinnerModel;
import com.kotcrab.vis.ui.widget.spinner.IntSpinnerModel;
import com.kotcrab.vis.ui.widget.spinner.SimpleFloatSpinnerModel;
import com.kotcrab.vis.ui.widget.spinner.Spinner;

public class TestWindow extends VisWindow {
    private static final Drawable white = VisUI.getSkin().getDrawable("white");

    public TestWindow () {
        super("example window");

        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();

        add(createLabels()).row();
        add(createButtons()).row();
        add(createCheckboxes()).row();
        add(createTextFields()).row();
        add(createProgressBars()).row();
        add(createSpinners()).row();
        add(createSelectBox()).row();
        if (Gdx.app.getType() == ApplicationType.Desktop) add(createFileChooser()).row();
        add(createColorPicker()).padBottom(3f);

        pack();
        centerWindow();
    }

    private VisTable createLabels () {
        VisLabel label = new VisLabel("label (hover for tooltip)");
        new Tooltip.Builder("this label has a tooltip").target(label).build();

        VisTable labelTable = new VisTable(true);
        labelTable.add(label);
        return labelTable;
    }

    private VisTable createButtons () {
        VisTextButton normalButton = new VisTextButton("button");
        VisTextButton normalBlueButton = new VisTextButton("button blue", "blue");
        VisTextButton toggleButton = new VisTextButton("toggle", "toggle");
        VisTextButton disabledButton = new VisTextButton("disabled");
        disabledButton.setDisabled(true);

        VisTable buttonTable = new VisTable(true);
        buttonTable.add(normalButton);
        buttonTable.add(normalBlueButton);
        buttonTable.add(toggleButton);
        buttonTable.add(disabledButton);
        return buttonTable;
    }

    private VisTable createCheckboxes () {
        VisCheckBox normalCheckbox = new VisCheckBox("checkbox");
        VisCheckBox disabledCheckbox = new VisCheckBox("disabled");
        disabledCheckbox.setDisabled(true);

        VisTable checkboxTable = new VisTable(true);
        checkboxTable.add(normalCheckbox);
        checkboxTable.add(disabledCheckbox);
        return checkboxTable;
    }

    private Actor createTextFields () {
        VisTextField normalTextField = new VisTextField("textbox");
        VisTextField disabledTextField = new VisTextField("disabled");
        VisTextField passwordTextField = new VisTextField("password");
        disabledTextField.setDisabled(true);
        passwordTextField.setPasswordMode(true);

        VisTable textFieldTable = new VisTable(true);
        textFieldTable.defaults().width(120);
        textFieldTable.add(normalTextField);
        textFieldTable.add(disabledTextField);
        textFieldTable.add(passwordTextField);
        return textFieldTable;
    }

    private Actor createProgressBars () {
        VisProgressBar progressbar = new VisProgressBar(0, 100, 1, false);
        VisSlider slider = new VisSlider(0, 100, 1, false);
        VisSlider sliderDisabled = new VisSlider(0, 100, 1, false);

        progressbar.setValue(50);
        slider.setValue(50);
        sliderDisabled.setValue(50);
        sliderDisabled.setDisabled(true);

        VisTable progressbarTable = new VisTable(true);
        progressbarTable.add(progressbar);
        progressbarTable.add(slider);
        progressbarTable.add(sliderDisabled);
        return progressbarTable;
    }

    private Actor createSpinners () {
        Array<String> stringArray = new Array<String>();
        stringArray.add("a");
        stringArray.add("b");
        stringArray.add("c");
        stringArray.add("d");
        stringArray.add("e");
        final ArraySpinnerModel<String> arrayModel = new ArraySpinnerModel<String>(stringArray);
        Spinner arraySpinner = new Spinner("array", arrayModel);

        final IntSpinnerModel intModel = new IntSpinnerModel(10, 5, 20, 2);
        Spinner intSpinner = new Spinner("integers", intModel);

        arraySpinner.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("changed array spinner to: " + arrayModel.getCurrent());
            }
        });

        intSpinner.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("changed int spinner to: " + intModel.getValue());
            }
        });

        VisTable spinnerTable = new VisTable(true);
        spinnerTable.add(intSpinner);
        spinnerTable.add(new Spinner("floats", new SimpleFloatSpinnerModel(10f, 5f, 20f, 1.5f, 1)));
        spinnerTable.add(arraySpinner);
        return spinnerTable;
    }

    private VisTable createSelectBox () {
        VisTable selectBoxTable = new VisTable(true);
        VisSelectBox<String> selectBox = new VisSelectBox<String>();
        selectBox.setItems("item 1", "item 2", "item 3", "item 4");

        selectBoxTable.add(new VisLabel("select box: "));
        selectBoxTable.add(selectBox);
        return selectBoxTable;
    }

    private VisTable createFileChooser () {
        // The following example can't be used on GWT, feel free to uncomment if you are not targeting GWT.

        // These imports must be added:
        //import com.kotcrab.vis.ui.widget.file.FileChooser;
        //import com.kotcrab.vis.ui.widget.file.SingleFileChooserListener;

        /*
        FileChooser.setFavoritesPrefsName("RPG");
        final FileChooser chooser = new FileChooser(FileChooser.Mode.OPEN);
        VisTextButton showButton = new VisTextButton("show file chooser");
        final VisLabel selectedFileLabel = new VisLabel("");

        chooser.setSelectionMode(FileChooser.SelectionMode.FILES);
        chooser.setMultiSelectionEnabled(false);
        chooser.setListener(new SingleFileChooserListener() {
            @Override
            protected void selected (FileHandle file) {
                Dialogs.showOKDialog(getStage(), "message", "selected: " + file.path());
            }
        });

        showButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                getStage().addActor(chooser.fadeIn());
            }
        });

        VisTable fileChooserTable = new VisTable(true);
        fileChooserTable.add(showButton);
        fileChooserTable.add(selectedFileLabel).width(100);
        return fileChooserTable;
        */
        return new VisTable();
    }

    private VisTable createColorPicker () {
        final Image image = new Image(white);
        final ColorPicker picker = new ColorPicker("color picker", new ColorPickerAdapter() {
            @Override
            public void finished (Color newColor) {
                image.setColor(newColor);
            }
        });

        VisTextButton showPickerButton = new VisTextButton("show color picker");
        showPickerButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                getStage().addActor(picker.fadeIn());
            }
        });

        Color c = new Color(27 / 255.0f, 161 / 255.0f, 226 / 255.0f, 1);
        picker.setColor(c);
        image.setColor(c);

        VisTable pickerTable = new VisTable(true);
        pickerTable.add(showPickerButton);
        pickerTable.add(image).size(32).pad(3);
        return pickerTable;
    }
}
