package com.suleimanov.vehiclecontrol.parameters.controllers;

import com.suleimanov.vehiclecontrol.parameters.models.Country;
import com.suleimanov.vehiclecontrol.parameters.services.CountryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parameters/countries")
public class CountryController {

  @Autowired
  private CountryService countryService;

//  @GetMapping()
//  public String getAll(Model model, String keyword) {
//    List<Country> countries = (keyword == null)
//            ? countryService.getCountries()
//            : countryService.getByKeyword(keyword);
//
//    model.addAttribute("countries", countries);
//    return "/parameters/country";
//  }

  @GetMapping()
  public String getAllPages(Model model, String keyword){
    return getOnePage(model, keyword, 1);
  }

  @GetMapping("/page/{pageNumber}")
  public String getOnePage(Model model, String keyword,
                           @PathVariable("pageNumber") int currentPage){
    Page<Country> page = (keyword == null)
            ? countryService.getPage(currentPage)
            : countryService.getPageByKeyword(currentPage, keyword);

    model.addAttribute("currentPage", currentPage);
    model.addAttribute("totalPages", page.getTotalPages());
    model.addAttribute("totalItems", page.getTotalElements());
    model.addAttribute("countries", page.getContent());
    return "/parameters/country";
  }

  @GetMapping("/page/{pageNumber}/{field}")
  public String getAllWithSort(Model model,@PathVariable("pageNumber") int currentPage,
                               @PathVariable("field") String field,
                               @PathParam("sortDir") String sortDir) {
    Page<Country> page = countryService.getCountriesWithSort(field, sortDir, currentPage);

    model.addAttribute("currentPage", currentPage);
    model.addAttribute("totalPages", page.getTotalPages());
    model.addAttribute("totalItems", page.getTotalElements());
    model.addAttribute("countries", page.getContent());
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    return "/parameters/country";
  }

  // The get Country by Id
  @GetMapping("/{id}")
  @ResponseBody
  public Country getCountry(@PathVariable Integer id) {
    return countryService.getById(id);
  }

  @GetMapping("/addNew")
  public String getAddNew() {
    return "/parameters/countryAdd";
  }

  @PostMapping()
  public String addNew(Country country) {
    countryService.save(country);
    return "redirect:/parameters/countries";
  }

  @GetMapping("/{op}/{id}")
  public String getEdit(@PathVariable Integer id, @PathVariable String op, Model model) {
    model.addAttribute("country", countryService.getById(id));
    return "/parameters/country" + op;
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
  public String delete(@PathVariable Integer id) {
    countryService.delete(id);
    return "redirect:/parameters/countries";
  }

//  @GetMapping("/parameters/country/{id}")
//  @ResponseBody
//  public List<Region> getRegionsByCountry(@PathVariable Integer id) {
//    Country country = countryService.getById(id);
//    if (country != null) {
//      return regionService.getRegionsByCountry(country);
//    } else {
//      return Collections.emptyList();
//    }
//  }
}
