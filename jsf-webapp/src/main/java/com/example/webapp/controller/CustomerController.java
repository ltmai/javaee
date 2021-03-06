package com.example.webapp.controller;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.webapp.entities.Customer;
import com.example.webapp.service.CustomerService;



@Named
@ViewScoped
public class CustomerController implements Serializable {

    private static final Logger logger = Logger.getLogger(CustomerController.class.getName());

    private static final long serialVersionUID = 1L;

    private static final  int PAGE_SIZE = 10;

    private static final int LAST_PAGE = -1;

    @Inject
    private CustomerService customerService;

    /**
     * customer instance being processed
     */
    private Customer customer = new Customer();

    private Long id;

    private int page = 1;

    private int pageCount = 1;

    /**
     * day/month/year to represent birthdate in create/edit form
     */
    private int dobDay;
    private int dobMonth;
    private String dobYear;

    /**
     * static data for day and month dropdown boxes
     */
    private static List<Integer> daysOfTheMonth = new ArrayList<>();

    static {
        for (int d = 1; d <= 31; ++d) {
            daysOfTheMonth.add(d);
        }
    }

    /**
     * Invoked on editing customer.
     * @param birthDate
     */
    public void setBirthDateToFormFields(Date birthDate) {
        if (birthDate != null) {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(customer.getBirthDate());
            dobDay = cal.get(Calendar.DAY_OF_MONTH);
            dobMonth = cal.get(Calendar.MONTH);
            dobYear = Integer.toString(cal.get(Calendar.YEAR));
        }
    }

    /**
     * bean method used in <f:viewAction> should be of the form
     * <code> public Object method() </code>
     * @return
     */
    public String findCustomerById() {
        System.out.println("findCustomerById");
        customer = customerService.findById(id);

        if (customer != null) {
            setBirthDateToFormFields(customer.getBirthDate());
        }
        return null;
    }

    public String cancel() {
        return "index.xhtml?faces-redirect=true";
    }

    private Date getBirthDateFromFormFields() {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, dobDay);
        cal.set(Calendar.MONTH, dobMonth);
        final int year = Integer.parseInt(dobYear);
        cal.set(Calendar.YEAR, year);
        return cal.getTime();
    }

    public String update() {
        customer.setBirthDate(getBirthDateFromFormFields());
        customerService.update(customer);

        return "index.xhtml?faces-redirect=true";
    }

    public String remove() {
        customerService.deleteById(id);
        return "index.xhtml?faces-redirect=true";
    }

    public void normalizePageIndex() {
        pageCount = (int)customerService.findNumberOfPages(PAGE_SIZE);
        
        switch(page) {
            case LAST_PAGE: 
                page = pageCount;
                break;
            case 0:
                page = 1;
                break;
            default:
                if (page > pageCount) {
                    page = pageCount;
                }
        }
    }

    public List<Customer> findAllCustomers() {
        logger.info("CustomerController::findAllCustomers");
        normalizePageIndex();

        return customerService.findAll(page, PAGE_SIZE);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getDobYear() {
        return dobYear;
    }

    public void setDobYear(String dobYear) {
        this.dobYear = dobYear;
    }

    public int getDobMonth() {
        return dobMonth;
    }

    public void setDobMonth(int dobMonth) {
        this.dobMonth = dobMonth;
    }

    public int getDobDay() {
        return dobDay;
    }

    public void setDobDay(int dobDay) {
        this.dobDay = dobDay;
    }

    public List<Integer> getDaysOfTheMonth() {
        return daysOfTheMonth;
    }

    public void setDaysOfTheMonth(List<Integer> daysOfTheMonth) {
        CustomerController.daysOfTheMonth = daysOfTheMonth;
    }

    public Map<String, Integer> getMonthsOfTheYear() {
        final Locale currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        final DateFormatSymbols symbols = DateFormatSymbols.getInstance(currentLocale);

        Map<String, Integer> monthsOfTheYear = new LinkedHashMap<>();
        for (int m = 0; m < 12; ++m) {
            monthsOfTheYear.put(symbols.getMonths()[m], m);
        }
        return monthsOfTheYear;
    }
}