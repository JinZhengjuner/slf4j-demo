//package com.jzj;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.ffcs.oss.entity.FaultCircuit;
//import com.ffcs.oss.entity.FaultCircuitLogTblBean;
//import com.ffcs.oss.mapper.FaultCircuitLogTblMapper;
//import com.ffcs.oss.mapper.InterruptQueryMapper;
//import com.ffcs.oss.service.InterruptQueryService;
//import com.ffcs.oss.utils.ThreadPoolUtils;
//import org.apache.commons.beanutils.BeanUtils;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import java.lang.reflect.InvocationTargetException;
//import java.security.PrivateKey;
//import java.sql.*;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.concurrent.*;
//import java.util.stream.Collectors;
//
//
//@Service
//public class InterruptQueryServiceImpl extends ServiceImpl<InterruptQueryMapper, FaultCircuit> implements InterruptQueryService {
//
//    private static final Logger log = LoggerFactory.getLogger(InterruptQueryServiceImpl.class);
//
//    @Autowired
//    private InterruptQueryMapper interruptQueryMapper;
//    @Autowired
//    private FaultCircuitLogTblMapper faultCircuitLogTblMapper;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//
//    public void queryCircuitFaultinfo() throws InvocationTargetException, IllegalAccessException, InterruptedException, SQLException, ClassNotFoundException {
////        interruptQueryMapper.deleteAllDate();
//        List<FaultCircuit> faultCircuits = interruptQueryMapper.queryCircuitFaultinfo();
//
//
//        log.info("faultCircuits是：" + faultCircuits.toString());
//        List<String> oldFaultCircuits = interruptQueryMapper.queryCircuitFaultinfos();
////        List<FaultCircuit> oldFaultCircuitsasdsdasd = interruptQueryMapper.queryCircuitFaultinfosqwq();
//        List<String> collectoldcuitAnidList = oldFaultCircuits.stream().distinct().collect(Collectors.toList());
////        log.info("oldFaultCircuits是：" + collectoldcuitAnidList);
////        log.info("oldFaultCircuits是：{}", oldFaultCircuits);
////        log.info("FaultCircuits长度是：" + faultCircuits.size());
//        log.info("collectoldFaultCircuits是：" + collectoldcuitAnidList);
//
//        Integer autoIncr = 8000000;
////        Integer autoIncr2 =8000000;
//
//        ArrayList<String> circuit_anidList2 = new ArrayList<>();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
//        long l = System.currentTimeMillis();
//        String format = simpleDateFormat.format(l);
////        List<FaultCircuit> faultCircuitsSend = new ArrayList<>();
//        List<String> anidList = new ArrayList<>();
//
//
//        for (FaultCircuit item : faultCircuits) {
//
//
//            String stringId = format + autoIncr;
//            autoIncr++;
//
//
//            String circuit_anid = item.getCircuit_anid();
//            circuit_anidList2.add(circuit_anid);
//            FaultCircuit faultCircuit = new FaultCircuit();
//
//            if (!collectoldcuitAnidList.contains(circuit_anid)) {
//
//                item.setId(stringId);
//                BeanUtils.copyProperties(faultCircuit, item);
//
//                String circuit_anid1 = faultCircuit.getCircuit_anid();
////                faultCircuitsSend.add(faultCircuit);
//                anidList.add(circuit_anid1);
//                interruptQueryMapper.insert(faultCircuit);
//                //调用存储过程
//                log.info("插入的链路故障是：" + faultCircuit);
//
////                log.info("collectoldFaultCircuits是："+collectoldFaultCircuits.toString());
//            }
//
//        }
//
//
//        /**
//         * 删除现在没有的数据，现在没得权限删除，给了权限打开
//         */
//        for (int i = 0; i < collectoldcuitAnidList.size(); i++) {
//            String scircuit_anid = collectoldcuitAnidList.get(i);
//            if (!circuit_anidList2.contains(scircuit_anid)) {
//
//                log.info("清除的list" + scircuit_anid);
//                boolean b = interruptQueryMapper.deleteAll(scircuit_anid);
//                log.info("清除成功");
//
//            }
//        }
//
//
//        ArrayList<String> circuit_anidList3 = new ArrayList<>();
//        List<String> oldFaultCircuits3 = interruptQueryMapper.queryCircuitFaultinfos();
//        log.info("oldFaultCircuits3是：" + oldFaultCircuits3);
//
//
//        for (String item : oldFaultCircuits3) {
//            if (item != null) {
//
//                circuit_anidList3.add(item);
//
//                log.info("circuit_anid" + item);
//            }
//        }
//
//
//        Map<String, List> vcidMap = new HashMap<>();
//        for (int i = 0; i < circuit_anidList3.size(); i++) {
//
//            String circuit_anid = circuit_anidList3.get(i);
//            FaultCircuit faultCircuit = interruptQueryMapper.queryCircuitFaultinfoByAnid(circuit_anid);
//            log.info("faultCircuit是：" + faultCircuit);
//
//            List<Map<String, String>> mapsList = interruptQueryMapper.queryIpranNewTbl(circuit_anid);
//            //log.info("mapsList是："+mapsList);
//
//            List<Map<String, String>> collect = mapsList.stream().distinct().collect(Collectors.toList());
//
//            log.info("collect：" + collect);
////            log.info("collect个数：" + collect.size());
//
//
//            List<Map<String, ArrayList>> ips2= new ArrayList<>();
//
//
//            for (int i1 = 0; i1 < collect.size(); i1++) {
////                List pwList=new ArrayList();
//                CopyOnWriteArrayList<String> pwList = new CopyOnWriteArrayList();
//
//                Map<String, String> map = collect.get(i1);
//
//                List<String> ips = new ArrayList();
//                String VCID = map.get("VCID");
//
//                String AIP1 = map.get("AIP1");
//                String BIP = map.get("BIP");
//                String ASBRIP = map.get("ASBRIP");
//                String TO_ASBRIP = map.get("TO_ASBRIP");
//                String to_BIP = map.get("TO_BIP");
//                String to_AIP1 = map.get("TO_AIP1");
//                String to_UIP = map.get("TO_UIP");
//                String UIP = map.get("UIP");
//                log.info("UIP::" + UIP);
////                ipAndvcid2.put(VCID,null);
//
//
//                log.info("map的结果是:" + map);
//
//                ips.add(AIP1);
//                ips.add(BIP);
//                ips.add(ASBRIP);
//                ips.add(TO_ASBRIP);
//                ips.add(to_BIP);
//                ips.add(to_AIP1);
//                ips.add(to_UIP);
//                ips.add(UIP);
//
//
//                ThreadPoolExecutor myExecutor = new ThreadPoolExecutor(
//                        1,
//                        ips.size(),
//                        10,
//                        TimeUnit.SECONDS,
//                        new SynchronousQueue<>()
//                );
//
//                try {
//                    if (!vcidMap.containsKey(VCID)) {
//                        pwList = changeInfo(ips, VCID, circuit_anid, faultCircuit, format, pwList, myExecutor);
//                    } else {
//                        List<String> arrayList = vcidMap.get(VCID);
//                        List<String> ips2 = new ArrayList<>();
//                        for (String ip : ips) {
//                            if (!arrayList.contains(ip)) {
//                                ips2.add(ip);
//                            }
//                        }
//                        pwList = changeInfo(ips2, VCID, circuit_anid, faultCircuit, format, pwList, myExecutor);
//                    }
//                } catch (Exception e) {
//                    log.info("多线程查询出错了...");
//                    throw e;
//                } finally {
//                    myExecutor.shutdown();
//                }
//
//
//                log.info("pwList=====" + pwList);
//                log.info("pwList=====" + pwList);
//                if (vcidMap.containsKey(VCID)) {
//                    List arrayList = vcidMap.get(VCID);
//                    arrayList.add(AIP1);
//                    arrayList.add(BIP);
//                    arrayList.add(ASBRIP);
//                    arrayList.add(TO_ASBRIP);
//                    arrayList.add(to_BIP);
//                    arrayList.add(to_AIP1);
//                    arrayList.add(to_UIP);
//                    arrayList.add(UIP);
//                    arrayList.stream().distinct().collect(Collectors.toList());
//                    vcidMap.put(VCID, arrayList);
//                } else {
//                    vcidMap.put(VCID, ips);
//                }
//
//
//                log.info("pwList的值是：" + pwList);
//                log.info("faultCircuit的值是：" + faultCircuit);
//                String anId = updateFat(pwList, faultCircuit);
//                log.info("数据入库成功结束");
//                log.info("数据更新入库成功结束，获取到发短信的链路故障名单");
//                if (anidList.contains(anId) && pwList.contains("down")) {
//                    log.info("数据更新入库成功结束，获取到发短信的链路故障名单");
//                    log.info("发送短信的anid名单" + anId);
//                    String circuittype = faultCircuit.getCircuittype();
//                    log.info("circuittype是：" + circuittype);
//                    log.info("开始判断是否发送短信");
//                    if (circuittype.toUpperCase().contains("STN")) {
//
//                        log.info("开始调用从存储过程");
//
////                        callSendMsg(faultCircuit);
//
//                        log.info("存储过程调用完毕");
//                    }
//
//                }
//
//
//            }
////            List<String> ips = new ArrayList<String>();
//
//
//        }
//
//    }
//
//
//    public CopyOnWriteArrayList<String> changeInfo(List<String> ips, String VCID, String circuit_anid, FaultCircuit faultCircuit, String format, CopyOnWriteArrayList<String> pwList, ThreadPoolExecutor myExecutor) throws InterruptedException, SQLException, ClassNotFoundException {
//        log.info("ips原始数据:{}", ips);
//        List<CompletableFuture<String>> completableFutureList = Optional.ofNullable(ips).orElse(new ArrayList<>())
//                .stream()
//                .filter(StringUtils::isNotBlank)
//                .peek(x -> log.info("过滤后的数据:{}", x))
//                .map(ip -> CompletableFuture.supplyAsync(() -> {
//                            HashMap<String, String> objectHashMap = new HashMap<>();
//                            objectHashMap.put("deviceIP", ip);
//                            objectHashMap.put("deviceId", ip);
//                            objectHashMap.put("jobid", "33333333");
//                            objectHashMap.put("mVcid", VCID);
//                            return queryGet(ip, VCID, objectHashMap, circuit_anid, format);
//                        }, myExecutor)
//                ).collect(Collectors.toList());
//        completableFutureList.stream()
//                .forEach(future -> {
//                    try {
//                        long startTime = System.currentTimeMillis();
//                        String pWstate = future.get(10, TimeUnit.MINUTES);
//                        log.info("queryGet返回的单个结果：{},耗时:{}", pWstate, (System.currentTimeMillis() - startTime));
//                        pwList.add(pWstate);
//                    } catch (InterruptedException | ExecutionException | TimeoutException e) {
//                        log.error("获取外部数据异常...添加一个ip");
//                        pwList.add("up");
//                    }
//                });
//        log.info("原始ips:{}, pwList的最终结果:{}", ips, pwList);
//        return pwList;
//    }
//
//    public String queryGet(String ip, String VCID, Map objectHashMap, String circuit_anid, String format) {
//        log.info("ip：" + ip);
//        log.info("VCID：" + VCID);
//
//
//        String pwstate = "";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-APP-ID", "b7de7d43f1f30aecd78ff05164697914");
//        headers.add("X-APP-KEY", "a237a7ec630ebe45a70a71b93fd7aee0");
//        headers.add("Content-Type", "application/json");
//        headers.add("X-CTG-Province-ID", "8510000");
//        headers.add("X-CTG-VERSION", "V1.0.00");
//
//        String url = "http://eop.paas.sc.ctc.com/serviceAgent/rest/uacp/ipran/stn/pw/get";
//
//
//        HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<MultiValueMap<String, Object>>(headers);
//        //3、组装请求头和参数
//        ResponseEntity<String> exchange = restTemplate.exchange(url + "?deviceIP=" + ip + "&deviceId=" + ip + "&jobid=33333333" + "&mVcid=" + VCID,
//                HttpMethod.GET, formEntity, String.class);
//        String body = exchange.getBody();
//        Map map1 = JSONObject.parseObject(body, Map.class);
//        log.info("调用接口返回的map1是：" + map1);
//        String pwSessionState = (String) map1.get("PwSessionState");
//        log.info("PwSessionState是：" + pwSessionState);
//        FaultCircuitLogTblBean faultCircuitLogTblBean = new FaultCircuitLogTblBean();
//        faultCircuitLogTblBean.setCircuit_anid(circuit_anid);
//        faultCircuitLogTblBean.setCall_xml(objectHashMap.toString());
//        log.info("入参是：" + objectHashMap);
//        faultCircuitLogTblBean.setReturn_xml(map1.toString());
//        faultCircuitLogTblBean.setCall_log_id(format);
//        faultCircuitLogTblMapper.insert(faultCircuitLogTblBean);
//        log.info("插入的日志数据是：" + faultCircuitLogTblBean);
//        if (map1.get("PwSessionState") != null) {
//            pwstate = pwSessionState.toLowerCase();
//        } else {
//        }
//        return pwstate;
//    }
//
//
//    public String updateFat(List<String> pwList, FaultCircuit faultCircuit) {
//
//
//        String circuitAnid = "";
//        if (pwList.contains("down") || pwList.contains("inactive")) {
//            log.info("更新前：" + faultCircuit);
//            circuitAnid = faultCircuit.getCircuit_anid();
//            faultCircuit.setPw_session_state("down");
//            faultCircuit.setInterrupt_state(1);
//            interruptQueryMapper.updateById(faultCircuit);
//
//            log.info("更新为：" + faultCircuit);
//        } else {
//
//            log.info("更新前为：" + faultCircuit);
//            faultCircuit.setPw_session_state("up");
////                faultCircuit.setPw_session_state(pwSessionState);
//            faultCircuit.setInterrupt_state(0);
//
//            interruptQueryMapper.updateById(faultCircuit);
//            log.info("更新为：" + faultCircuit);
//        }
//
//        log.info("faultCircuit====>" + faultCircuit);
//
//
//        return circuitAnid;
//
//
//    }
//
//
//    public boolean callSendMsg(FaultCircuit faultCircuit) throws ClassNotFoundException, SQLException {
//
//
//        Class.forName("oracle.jdbc.OracleDriver");
//        String cust_name = faultCircuit.getCust_name();
//        String circuit_anid = faultCircuit.getCircuit_anid();
//        String org_time = faultCircuit.getOrg_time();
//        String dev_adress = faultCircuit.getDev_adress();
//        String str = cust_name + "电路编码为" + circuit_anid + "的电路在" + org_time + "发生中断，故障定位信息为" + dev_adress + "，请核实！";
//
//        log.info("发送的短信内容是：" + str);
//
//
//        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@133.37.22.171:1521:SCWG", "anmsuis", "U4321is!");
////            log.info("第一步结束");
//        Statement stmt = conn.createStatement();
//        //String sql = "{call ims_rms.proc_msg(?,?)}";
//        log.info("第2步结束");
//        CallableStatement cstmt = conn.prepareCall("{call inms_rms.proc_msg(?,?)}");
//        log.info("第3步结束");
//        cstmt.setInt(1, 5);
//        cstmt.setString(2, str);
//        log.info("第4步结束，开始执行");
//        boolean execute = cstmt.execute();
//        log.info("调用存储过程是否成功" + execute);
//
//
//        cstmt.close();
//        conn.close();
//
//        return execute;
//
//
//    }
//}