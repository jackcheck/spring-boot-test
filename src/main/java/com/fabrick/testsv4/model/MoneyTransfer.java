package com.fabrick.testsv4.model;

import lombok.Data;

import java.util.Date;

@Data
public class MoneyTransfer {

    public static Creditor creditor;
//    public static Date executionDate;
    public static String executionDate;
    public static String uri;
    public static String description;
    public static int amount;
    public static String currency;
    public static boolean isUrgent;
    public static boolean isInstant;
    public static String feeType;
    public static String feeAccountId;
    public static TaxRelief taxRelief;

    public Creditor getCreditor() {
        return creditor;
    }

    public void setCreditor(Creditor creditor) {
        this.creditor = creditor;
    }

//    public Date getExecutionDate() {
//        return executionDate;
//    }
//
//    public void setExecutionDate(Date executionDate) {
//        this.executionDate = executionDate;
//    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isUrgent() {
        return isUrgent;
    }

    public void setUrgent(boolean urgent) {
        isUrgent = urgent;
    }

    public boolean isInstant() {
        return isInstant;
    }

    public void setInstant(boolean instant) {
        isInstant = instant;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getFeeAccountId() {
        return feeAccountId;
    }

    public void setFeeAccountId(String feeAccountId) {
        this.feeAccountId = feeAccountId;
    }

    public TaxRelief getTaxRelief() {
        return taxRelief;
    }

    public void setTaxRelief(TaxRelief taxRelief) {
        this.taxRelief = taxRelief;
    }

    public static class Creditor{
        public String name;
        public Account account;
        public Address address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public static class Account{
            public String accountCode;
            public String bicCode;

            public String getAccountCode() {
                return accountCode;
            }

            public void setAccountCode(String accountCode) {
                this.accountCode = accountCode;
            }

            public String getBicCode() {
                return bicCode;
            }

            public void setBicCode(String bicCode) {
                this.bicCode = bicCode;
            }
        }

        public static class Address{
            public String address;
            public String city;
            public String countryCode;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCountryCode() {
                return countryCode;
            }

            public void setCountryCode(String countryCode) {
                this.countryCode = countryCode;
            }
        }

    }

    public static class TaxRelief{
        public String taxReliefId;
        public boolean isCondoUpgrade;
        public String creditorFiscalCode;
        public String beneficiaryType;
        public NaturalPersonBeneficiary naturalPersonBeneficiary;
        public LegalPersonBeneficiary legalPersonBeneficiary;

        public String getTaxReliefId() {
            return taxReliefId;
        }

        public void setTaxReliefId(String taxReliefId) {
            this.taxReliefId = taxReliefId;
        }

        public boolean isCondoUpgrade() {
            return isCondoUpgrade;
        }

        public void setCondoUpgrade(boolean condoUpgrade) {
            isCondoUpgrade = condoUpgrade;
        }

        public String getCreditorFiscalCode() {
            return creditorFiscalCode;
        }

        public void setCreditorFiscalCode(String creditorFiscalCode) {
            this.creditorFiscalCode = creditorFiscalCode;
        }

        public String getBeneficiaryType() {
            return beneficiaryType;
        }

        public void setBeneficiaryType(String beneficiaryType) {
            this.beneficiaryType = beneficiaryType;
        }

        public NaturalPersonBeneficiary getNaturalPersonBeneficiary() {
            return naturalPersonBeneficiary;
        }

        public void setNaturalPersonBeneficiary(NaturalPersonBeneficiary naturalPersonBeneficiary) {
            this.naturalPersonBeneficiary = naturalPersonBeneficiary;
        }

        public LegalPersonBeneficiary getLegalPersonBeneficiary() {
            return legalPersonBeneficiary;
        }

        public void setLegalPersonBeneficiary(LegalPersonBeneficiary legalPersonBeneficiary) {
            this.legalPersonBeneficiary = legalPersonBeneficiary;
        }

        public static class NaturalPersonBeneficiary{
            public String fiscalCode1;
            public String fiscalCode2;
            public String fiscalCode3;
            public String fiscalCode4;
            public String fiscalCode5;

            public String getFiscalCode1() {
                return fiscalCode1;
            }

            public void setFiscalCode1(String fiscalCode1) {
                this.fiscalCode1 = fiscalCode1;
            }

            public String getFiscalCode2() {
                return fiscalCode2;
            }

            public void setFiscalCode2(String fiscalCode2) {
                this.fiscalCode2 = fiscalCode2;
            }

            public String getFiscalCode3() {
                return fiscalCode3;
            }

            public void setFiscalCode3(String fiscalCode3) {
                this.fiscalCode3 = fiscalCode3;
            }

            public String getFiscalCode4() {
                return fiscalCode4;
            }

            public void setFiscalCode4(String fiscalCode4) {
                this.fiscalCode4 = fiscalCode4;
            }

            public String getFiscalCode5() {
                return fiscalCode5;
            }

            public void setFiscalCode5(String fiscalCode5) {
                this.fiscalCode5 = fiscalCode5;
            }
        }

        public static class LegalPersonBeneficiary{
            public String fiscalCode;
            public String legalRepresentativeFiscalCode;

            public String getFiscalCode() {
                return fiscalCode;
            }

            public void setFiscalCode(String fiscalCode) {
                this.fiscalCode = fiscalCode;
            }

            public String getLegalRepresentativeFiscalCode() {
                return legalRepresentativeFiscalCode;
            }

            public void setLegalRepresentativeFiscalCode(String legalRepresentativeFiscalCode) {
                this.legalRepresentativeFiscalCode = legalRepresentativeFiscalCode;
            }
        }

    }


}









