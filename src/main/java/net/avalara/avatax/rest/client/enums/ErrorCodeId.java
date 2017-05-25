package net.avalara.avatax.rest.client.enums;

/**
 * 
 */
public enum ErrorCodeId {
    /**
     * No comment data provided
     */
    ServerConfiguration,
    
    /**
     * No comment data provided
     */
    AccountInvalidException,
    
    /**
     * No comment data provided
     */
    CompanyInvalidException,
    
    /**
     * No comment data provided
     */
    EntityNotFoundError,
    
    /**
     * No comment data provided
     */
    ValueRequiredError,
    
    /**
     * No comment data provided
     */
    RangeError,
    
    /**
     * No comment data provided
     */
    RangeCompareError,
    
    /**
     * No comment data provided
     */
    RangeSetError,
    
    /**
     * No comment data provided
     */
    TaxpayerNumberRequired,
    
    /**
     * No comment data provided
     */
    CommonPassword,
    
    /**
     * No comment data provided
     */
    WeakPassword,
    
    /**
     * No comment data provided
     */
    StringLengthError,
    
    /**
     * No comment data provided
     */
    EmailValidationError,
    
    /**
     * No comment data provided
     */
    EmailMissingError,
    
    /**
     * No comment data provided
     */
    ParserFieldNameError,
    
    /**
     * No comment data provided
     */
    ParserFieldValueError,
    
    /**
     * No comment data provided
     */
    ParserSyntaxError,
    
    /**
     * No comment data provided
     */
    ParserTooManyParametersError,
    
    /**
     * No comment data provided
     */
    ParserUnterminatedValueError,
    
    /**
     * No comment data provided
     */
    DeleteUserSelfError,
    
    /**
     * No comment data provided
     */
    OldPasswordInvalid,
    
    /**
     * No comment data provided
     */
    CannotChangePassword,
    
    /**
     * No comment data provided
     */
    CannotChangeCompanyCode,
    
    /**
     * No comment data provided
     */
    DateFormatError,
    
    /**
     * No comment data provided
     */
    NoDefaultCompany,
    
    /**
     * No comment data provided
     */
    AuthenticationException,
    
    /**
     * No comment data provided
     */
    AuthorizationException,
    
    /**
     * No comment data provided
     */
    ValidationException,
    
    /**
     * No comment data provided
     */
    InactiveUserError,
    
    /**
     * No comment data provided
     */
    AuthenticationIncomplete,
    
    /**
     * No comment data provided
     */
    BasicAuthIncorrect,
    
    /**
     * No comment data provided
     */
    IdentityServerError,
    
    /**
     * No comment data provided
     */
    BearerTokenInvalid,
    
    /**
     * No comment data provided
     */
    ModelRequiredException,
    
    /**
     * No comment data provided
     */
    AccountExpiredException,
    
    /**
     * No comment data provided
     */
    VisibilityError,
    
    /**
     * No comment data provided
     */
    BearerTokenNotSupported,
    
    /**
     * No comment data provided
     */
    InvalidSecurityRole,
    
    /**
     * No comment data provided
     */
    InvalidRegistrarAction,
    
    /**
     * No comment data provided
     */
    RemoteServerError,
    
    /**
     * No comment data provided
     */
    NoFilterCriteriaException,
    
    /**
     * No comment data provided
     */
    OpenClauseException,
    
    /**
     * No comment data provided
     */
    JsonFormatError,
    
    /**
     * No comment data provided
     */
    UnhandledException,
    
    /**
     * No comment data provided
     */
    ReportingCompanyMustHaveContactsError,
    
    /**
     * No comment data provided
     */
    CompanyProfileNotSet,
    
    /**
     * No comment data provided
     */
    ModelStateInvalid,
    
    /**
     * No comment data provided
     */
    DateRangeError,
    
    /**
     * No comment data provided
     */
    InvalidDateRangeError,
    
    /**
     * No comment data provided
     */
    DeleteInformation,
    
    /**
     * No comment data provided
     */
    CannotCreateDeletedObjects,
    
    /**
     * No comment data provided
     */
    CannotModifyDeletedObjects,
    
    /**
     * No comment data provided
     */
    ReturnNameNotFound,
    
    /**
     * No comment data provided
     */
    InvalidAddressTypeAndCategory,
    
    /**
     * No comment data provided
     */
    DefaultCompanyLocation,
    
    /**
     * No comment data provided
     */
    InvalidCountry,
    
    /**
     * No comment data provided
     */
    InvalidCountryRegion,
    
    /**
     * No comment data provided
     */
    BrazilValidationError,
    
    /**
     * No comment data provided
     */
    BrazilExemptValidationError,
    
    /**
     * No comment data provided
     */
    BrazilPisCofinsError,
    
    /**
     * No comment data provided
     */
    JurisdictionNotFoundError,
    
    /**
     * No comment data provided
     */
    MedicalExciseError,
    
    /**
     * No comment data provided
     */
    RateDependsTaxabilityError,
    
    /**
     * No comment data provided
     */
    RateDependsEuropeError,
    
    /**
     * No comment data provided
     */
    InvalidRateTypeCode,
    
    /**
     * No comment data provided
     */
    RateTypeNotSupported,
    
    /**
     * No comment data provided
     */
    CannotUpdateNestedObjects,
    
    /**
     * No comment data provided
     */
    UPCCodeInvalidChars,
    
    /**
     * No comment data provided
     */
    UPCCodeInvalidLength,
    
    /**
     * No comment data provided
     */
    IncorrectPathError,
    
    /**
     * No comment data provided
     */
    InvalidJurisdictionType,
    
    /**
     * No comment data provided
     */
    MustConfirmResetLicenseKey,
    
    /**
     * No comment data provided
     */
    DuplicateCompanyCode,
    
    /**
     * No comment data provided
     */
    TINFormatError,
    
    /**
     * No comment data provided
     */
    DuplicateNexusError,
    
    /**
     * No comment data provided
     */
    UnknownNexusError,
    
    /**
     * No comment data provided
     */
    ParentNexusNotFound,
    
    /**
     * No comment data provided
     */
    InvalidTaxCodeType,
    
    /**
     * No comment data provided
     */
    CannotActivateCompany,
    
    /**
     * No comment data provided
     */
    DuplicateEntityProperty,
    
    /**
     * No comment data provided
     */
    ReportingEntityError,
    
    /**
     * No comment data provided
     */
    InvalidReturnOperationError,
    
    /**
     * No comment data provided
     */
    CannotDeleteCompany,
    
    /**
     * No comment data provided
     */
    CountryOverridesNotAvailable,
    
    /**
     * No comment data provided
     */
    JurisdictionOverrideMismatch,
    
    /**
     * No comment data provided
     */
    DuplicateSystemTaxCode,
    
    /**
     * No comment data provided
     */
    SSTOverridesNotAvailable,
    
    /**
     * No comment data provided
     */
    NexusDateMismatch,
    
    /**
     * No comment data provided
     */
    TechSupportAuditRequired,
    
    /**
     * No comment data provided
     */
    NexusParentDateMismatch,
    
    /**
     * No comment data provided
     */
    BearerTokenParseUserIdError,
    
    /**
     * No comment data provided
     */
    RetrieveUserError,
    
    /**
     * No comment data provided
     */
    InvalidConfigurationSetting,
    
    /**
     * No comment data provided
     */
    InvalidConfigurationValue,
    
    /**
     * No comment data provided
     */
    InvalidEnumValue,
    
    /**
     * No comment data provided
     */
    TaxCodeAssociatedTaxRule,
    
    /**
     * No comment data provided
     */
    CannotSwitchAccountId,
    
    /**
     * No comment data provided
     */
    RequestIncomplete,
    
    /**
     * No comment data provided
     */
    AccountNotNew,
    
    /**
     * No comment data provided
     */
    PasswordLengthInvalid,
    
    /**
     * No comment data provided
     */
    BatchSalesAuditMustBeZippedError,
    
    /**
     * No comment data provided
     */
    BatchZipMustContainOneFileError,
    
    /**
     * No comment data provided
     */
    BatchInvalidFileTypeError,
    
    /**
     * No comment data provided
     */
    PointOfSaleFileSize,
    
    /**
     * No comment data provided
     */
    PointOfSaleSetup,
    
    /**
     * No comment data provided
     */
    GetTaxError,
    
    /**
     * No comment data provided
     */
    AddressConflictException,
    
    /**
     * No comment data provided
     */
    DocumentCodeConflict,
    
    /**
     * No comment data provided
     */
    MissingAddress,
    
    /**
     * No comment data provided
     */
    InvalidParameter,
    
    /**
     * No comment data provided
     */
    InvalidParameterValue,
    
    /**
     * No comment data provided
     */
    CompanyCodeConflict,
    
    /**
     * No comment data provided
     */
    DocumentFetchLimit,
    
    /**
     * No comment data provided
     */
    AddressIncomplete,
    
    /**
     * No comment data provided
     */
    AddressLocationNotFound,
    
    /**
     * No comment data provided
     */
    MissingLine,
    
    /**
     * No comment data provided
     */
    InvalidAddressTextCase,
    
    /**
     * No comment data provided
     */
    DocumentNotCommitted,
    
    /**
     * No comment data provided
     */
    MultiDocumentTypesError,
    
    /**
     * No comment data provided
     */
    InvalidDocumentTypesToFetch,
    
    /**
     * No comment data provided
     */
    BadDocumentFetch,
    
    /**
     * No comment data provided
     */
    ServerUnreachable,
    
    /**
     * No comment data provided
     */
    SubscriptionRequired,
    
    /**
     * No comment data provided
     */
    AccountExists,
    
    /**
     * No comment data provided
     */
    InvitationOnly,
    
    /**
     * No comment data provided
     */
    ZTBListConnectorFail,
    
    /**
     * No comment data provided
     */
    ZTBCreateSubscriptionsFail,
    
    /**
     * No comment data provided
     */
    FreeTrialNotAvailable,
    
    /**
     * No comment data provided
     */
    InvalidDocumentStatusForRefund,
    
    /**
     * No comment data provided
     */
    RefundTypeAndPercentageMismatch,
    
    /**
     * No comment data provided
     */
    InvalidDocumentTypeForRefund,
    
    /**
     * No comment data provided
     */
    RefundTypeAndLineMismatch,
    
    /**
     * No comment data provided
     */
    NullRefundPercentageAndLines,
    
    /**
     * No comment data provided
     */
    InvalidRefundType,
    
    /**
     * No comment data provided
     */
    RefundPercentageForTaxOnly,
    
    /**
     * No comment data provided
     */
    LineNoOutOfRange,
    
    /**
     * No comment data provided
     */
    RefundPercentageOutOfRange,
    
    /**
     * No comment data provided
     */
    TaxRateNotAvailableForFreeInThisCountry,
    
    /**
     * No comment data provided
     */
    FilingCalendarCannotBeDeleted,
    
    /**
     * No comment data provided
     */
    InvalidEffectiveDate,
    
    /**
     * No comment data provided
     */
    NonOutletForm,
    
    /**
     * No comment data provided
     */
    QuestionNotNeededForThisAddress,
    
    /**
     * No comment data provided
     */
    QuestionNotValidForThisAddress,
    
    /**
     * No comment data provided
     */
    CannotModifyLockedTransaction,
    
    /**
     * No comment data provided
     */
    LineAlreadyExists,
    
    /**
     * No comment data provided
     */
    LineDoesNotExist,
    
    /**
     * No comment data provided
     */
    LinesNotSpecified,
    

}
    