package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);

        if (searchType.equals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            model.addAttribute("title", "All Jobs");
            model.addAttribute("jobs", jobs);
            int jobSize = jobs.size();
            model.addAttribute("jobSize", jobSize);
        } else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "All " + ListController.columnChoices.get(searchType) + " Values");
            model.addAttribute("jobs", jobs);
            int jobSize = jobs.size();
            model.addAttribute("jobSize", jobSize);
        }

        return "search";
    }
}

