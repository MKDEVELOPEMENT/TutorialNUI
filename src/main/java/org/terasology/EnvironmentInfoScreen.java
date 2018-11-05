/*
 * Copyright 2018 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology;

import org.terasology.engine.Time;
import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UISlider;
import org.terasology.rendering.nui.widgets.UISliderOnChangeTriggeredListener;
import org.terasology.rendering.nui.widgets.UIText;

public class EnvironmentInfoScreen extends CoreScreenLayer {
    private UIText infoArea;
    private UISlider fpsSlide;
    private UIText desiredTxt;
    private UIButton updateInfoButton;

    @In
    private Time time;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        fpsSlide = find("FPSSlide", UISlider.class);
        desiredTxt = find("desiredTxt", UIText.class);
        updateInfoButton = find("updateFPSButton", UIButton.class);

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
                infoArea.setText(String.format("Your current FPS is: %.0f",
                        time.getFps()));
            });
        }

        fpsSlide.setUiSliderOnChangeTriggeredListener(val -> {
            desiredTxt.setText(String.format("Your desired FPS is %.0f", val));
        });
    }
}
