package org.openmrs.module.registrationapp.fragment.controller.field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;

public class DropDownFragmentController {

    public void controller(FragmentConfiguration config) throws Exception {
        if (config.getAttribute("conceptSet") != null) {
            List < Map < String, Object >> options = (List<Map<String, Object>>) config.getAttribute("options");
            if (options == null) {
            	options = new ArrayList < Map < String, Object >> ();
            }
            String conceptSetUuid = (String) config.getAttribute("conceptSet");
            Concept conceptSet = Context.getConceptService().getConceptByUuid(conceptSetUuid);

            for (Concept concept: conceptSet.getSetMembers()) {
            	Map < String, Object > option = new HashMap < String, Object > ();
                option = new HashMap < String, Object > ();
                option.put("value", concept.getId().toString());
                option.put("label", concept.getName(Context.getUserContext().getLocale()).getName());
                options.add(option);
            }
            config.addAttribute("options", options);
        }
    }
}