/*============================================================================
 *
 * Copyright (c) 2000-2017 Smart Trade Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of Smart Trade Technologies
 * Use is subject to license terms. Duplication or distribution prohibited.
 *
 *============================================================================*/

package com.smarttrade.connectivity.kastorfx.worker;

/**
 * MessageList DOCUMENT ME
 *
 * @author jdeng
 */
public enum MessageList {

    ExecutionReport, NewOrderSingle, OrderCancelReplaceRequest, OrderCancelRequest, MarketDataRequest, CancelMarketDataRequest,
    QuoteRequest, CancelQuoteRequest, UpdateQuoteRequest, QuoteMultiLegRequest, CancelQuoteMultiLegRequest, NewOrderMultiLeg,
    ExecutionReportMultiLeg, OrderListRequest, UserStatusListRequest, SecurityListRequest, TradingSessionListRequest, OrderList,
    TradeCancelRequest, NegotiationNotification, NegotiationAction, NegotiationActionReport;

}
