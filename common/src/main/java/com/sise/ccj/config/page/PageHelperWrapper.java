package com.sise.ccj.config.page;

import com.github.pagehelper.PageHelper;

import java.util.Map;
import java.util.Properties;

/**
 *
 * PageHelper的封装类，帮助快速获取一个PageHelper实例.
 * TODO 后续希望本插件能单独抽离出去
 *
 * 重要提示：
 * 当offsetAsPageNum=false的时候，由于PageNum问题，RowBounds查询的时候reasonable会强制为false。使用PageHelper.startPage方法不受影响。
 * 另外使用RowBounds在这种情况下返回的Page对象由于没有正确的pageNum属性，所以也不能使用PageInfo处理。
 * 如果你不理解为什么，可以看这样一个例子：查询offset=7,limit=10，这个时候pageNum=?，这种情况没法计算pageNum，没法判断当前是第几页。
 */
public class PageHelperWrapper {

    // properties to hold all attributes
    private Properties properties;

    // no constructor should be exposed
    private PageHelperWrapper() {}

    /**
     * 新建封装类实例
     *
     * @return PageHelperWrapper
     */
    public static PageHelperWrapper newInstance() {
        PageHelperWrapper wrapper = new PageHelperWrapper();
        Properties p = new Properties();
        wrapper.properties = p;
        return wrapper;
    }

    /**
     * 增加dialect属性，使用时可以指定该属性（不指定的情况下，分页插件会自动判断）
     * 可选值为oracle,mysql,mariadb,sqlite,hsqldb,postgresql,db2,sqlserver,informix,h2,sqlserver2012
     *
     * @param dialect 类型
     * @return PageHelper封装类
     */
    public PageHelperWrapper setDialect(Dialect dialect) {
        properties.setProperty("dialect", dialect.getType());
        return this;
    }

    /**
     * 增加offsetAsPageNum属性，默认值为false。
     * 使用默认值时不需要增加该配置，需要设为true时，需要配置该参数。
     * 当该参数设置为true时，使用RowBounds分页时，会将offset参数当成pageNum使用，可以用页码和页面大小两个参数进行分页。
     *
     * @param offsetAsPageNum true | false
     * @return PageHelper封装类
     */
    public PageHelperWrapper setOffsetAsPageNum(boolean offsetAsPageNum) {
        properties.setProperty("offsetAsPageNum", String.valueOf(offsetAsPageNum));
        return this;
    }

    /**
     * 增加rowBoundsWithCount属性，默认值为false。
     * 使用默认值时不需要增加该配置，需要设为true时，需要配置该参数。当该参数设置为true时，使用RowBounds分页会进行count查询
     *
     * @param rowBoundsWithCount true | false
     * @return PageHelper封装类
     */
    public PageHelperWrapper setRowBoundsWithCount(boolean rowBoundsWithCount) {
        properties.setProperty("rowBoundsWithCount", String.valueOf(rowBoundsWithCount));
        return this;
    }

    /**
     * 增加pageSizeZero属性，默认值为false。
     * 使用默认值时不需要增加该配置，需要设为true时，需要配置该参数。
     * 当该参数设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果（相当于没有执行分页查询，但是返回结果仍然是Page类型）
     *
     * @param pageSizeZero true | false
     * @return PageHelper封装类
     */
    public PageHelperWrapper setPageSizeZero(boolean pageSizeZero) {
        properties.setProperty("pageSizeZero", String.valueOf(pageSizeZero));
        return this;
    }

    /**
     * 增加reasonable属性，默认值为false。
     * 使用默认值时不需要增加该配置，需要设为true时，需要配置该参数。
     * 启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
     * 禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据
     *
     * @param reasonable true | false
     * @return PageHelper封装类
     */
    public PageHelperWrapper setReasonable(boolean reasonable) {
        properties.setProperty("reasonable", String.valueOf(reasonable));
        return this;
    }

    /**
     * 为了支持startPage(Object params)方法，增加了一个params参数来配置参数映射，
     * 用于从Map或ServletRequest中取值，可以配置pageNum,size,count,pageSizeZero,reasonable,orderBy,不配置映射的用默认值
     * 不理解该含义的前提下，不要随便复制该配置
     *
     * @param params 额外参数
     * @return PageHelper封装类
     */
    public PageHelperWrapper setParams(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Object> param : params.entrySet()) {
            sb.append(param.getKey());
            sb.append("=");
            sb.append(String.valueOf(param.getValue()));
            sb.append(";");
        }
        properties.setProperty("params", sb.toString());
        return this;
    }

    /**
     * returnPageInfo用来支持直接返回PageInfo类型，默认值none。
     * 可选参数always总是返回PageInfo类型,check检查返回类型是否为PageInfo,none返回Page(List)类型。
     * 用法和配置参考com.github.pagehelper.test.basic包下的PageInfoTest，特别要注意接口的返回值和xml中的resultType类型
     *
     * @param returnPageInfo returnPageInfo
     * @return PageHelper封装类
     */
    public PageHelperWrapper setReturnPageInfo(ReturnPageInfo returnPageInfo) {
        properties.setProperty("returnPageInfo", returnPageInfo.getType());
        return this;
    }


    /**
     * 根据封装类的参数形成一个PageHelper并返回
     *
     * @return PageHelper
     */
    public PageHelper get() {
        PageHelper pageHelper = new PageHelper();
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
