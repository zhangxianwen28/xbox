package com.xw.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xw.swing.elastic.domain.bo.EsType;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.util.*;

public class ESJSONParser {

    public static void main(String[] args) {

        JSONObject parse = (JSONObject) JSON.parse(getJson());
        List<String> list = new ArrayList<>();
        Object o = parse.get("mappings");
        Queue<String> queue = new ArrayDeque<>();
        queue.add("mappings");
        queue.add("properties");
        Object properties = selectByPath(parse, queue);
        list.forEach(System.out::println);

    }

    private static Object selectByPath(JSONObject parse, Queue<String> queue) {
        if (parse == null) {
            return null;
        }
        JSONObject ojb = parse;
        for (String s : queue) {
            Object o = ojb.get(s);
            if (o instanceof JSONObject) {
                ojb = (JSONObject) o;
            }
        }
        return ojb;
    }

    private static Object select(JSONObject parse, String name) {
        if (parse == null) {
            return null;
        }
        Object ojb = null;
        Set<Map.Entry<String, Object>> entries = parse.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (name.equals(key)) {
                ojb = value;
                break;
            } else if (value instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) value;
                ojb = select(jsonObject, name);
            }
        }
        return ojb;
    }

    private static void getProperties(Object object, List<String> list) {
        if (!(object instanceof JSONObject)) {
            return;
        }
        JSONObject prop = (JSONObject) object;
        prop.forEach((key, value) -> {
            if ("properties".equals(key)) {
                getProperties(value, list);
            } else {
                list.add(key);
            }
        });

    }

    public static String getJson() {

        return "{  \"settings\": {\n" +
                "  \"number_of_shards\": 1,\n" +
                "  \"number_of_replicas\": 0,\n" +
                "  \"max_result_window\": 10000000,\n" +
                "  \"analysis\": {\n" +
                "    \"normalizer\": {\n" +
                "      \"my_normalizer\": {\n" +
                "        \"type\": \"custom\",\n" +
                "        \"char_filter\": [],\n" +
                "        \"filter\": [\n" +
                "          \"lowercase\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    \"analyzer\": {\n" +
                "      \"my_analyzer\": {\n" +
                "        \"tokenizer\": \"standard\",\n" +
                "        \"char_filter\": [\n" +
                "          \"html_strip\"\n" +
                "        ],\n" +
                "        \"filter\": [\n" +
                "          \"lowercase\",\n" +
                "          \"english_stop\",\n" +
                "          \"kstem\"\n" +
                "        ]\n" +
                "      },\n" +
                "      \"ik_analyzer\": {\n" +
                "        \"tokenizer\": \"ik_smart\",\n" +
                "        \"char_filter\": [\n" +
                "          \"html_strip\"\n" +
                "        ],\n" +
                "        \"filter\": [\n" +
                "          \"lowercase\",\n" +
                "          \"english_stop\",\n" +
                "          \"kstem\"\n" +
                "        ]\n" +
                "      },\n" +
                "      \"ik_limit_analyzer\": {\n" +
                "        \"tokenizer\": \"ik_smart\",\n" +
                "        \"char_filter\": [\n" +
                "          \"html_strip\"\n" +
                "        ],\n" +
                "        \"filter\": [\n" +
                "          \"lowercase\",\n" +
                "          \"english_stop\",\n" +
                "          \"word_delimiter\",\n" +
                "          \"kstem\"\n" +
                "        ]\n" +
                "      },\n" +
                "      \"my_sort_analyzer\": {\n" +
                "        \"tokenizer\": \"keyword\",\n" +
                "        \"char_filter\": [\n" +
                "          \"html_strip\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    \"filter\": {\n" +
                "      \"english_stop\": {\n" +
                "        \"type\": \"stop\",\n" +
                "        \"stopwords\": \"_english_\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "},\n" +
                "  \"mappings\" : {\n" +
                "    \"dynamic\" : \"false\",\n" +
                "    \"properties\" : {\n" +
                "      \"abstr_search\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"absContent\" : {\n" +
                "            \"type\" : \"text\"\n" +
                "          },\n" +
                "          \"absLanguage\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"pubId\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"applicantCountryList\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"_aggs\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"name\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"applicantNameYuan_search\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"applicantName_search\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"applyDate\" : {\n" +
                "        \"type\" : \"date\"\n" +
                "      },\n" +
                "      \"applyDateObj\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"_aggs\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"applyYear\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"applyNo_search\" : {\n" +
                "        \"type\" : \"text\",\n" +
                "        \"copy_to\" : [\n" +
                "          \"fullText._suggest\"\n" +
                "        ],\n" +
                "        \"analyzer\" : \"ik_limit_analyzer\"\n" +
                "      },\n" +
                "      \"applyOrgObj\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"_aggs\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"name\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"availability\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"availability_aggs\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"claimList\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"claimContents\" : {\n" +
                "            \"type\" : \"text\"\n" +
                "          },\n" +
                "          \"claimTree\" : {\n" +
                "            \"type\" : \"text\"\n" +
                "          },\n" +
                "          \"id\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"langueSn\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"pubId\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"cpcCode_search\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"drawingList\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"eng_no\" : {\n" +
                "            \"type\" : \"integer\"\n" +
                "          },\n" +
                "          \"pubId\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"start_no\" : {\n" +
                "            \"type\" : \"integer\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"drugList\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"_aggs\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"aliasName_search\" : {\n" +
                "            \"type\" : \"text\",\n" +
                "            \"copy_to\" : [\n" +
                "              \"fullText_suggest\"\n" +
                "            ],\n" +
                "            \"analyzer\" : \"ik_analyzer\"\n" +
                "          },\n" +
                "          \"brandName_search\" : {\n" +
                "            \"type\" : \"text\",\n" +
                "            \"copy_to\" : [\n" +
                "              \"fullText_suggest\"\n" +
                "            ],\n" +
                "            \"analyzer\" : \"ik_analyzer\"\n" +
                "          },\n" +
                "          \"casCode_search\" : {\n" +
                "            \"type\" : \"text\",\n" +
                "            \"copy_to\" : [\n" +
                "              \"fullText_suggest\"\n" +
                "            ],\n" +
                "            \"analyzer\" : \"ik_analyzer\"\n" +
                "          },\n" +
                "          \"cnName_search\" : {\n" +
                "            \"type\" : \"text\",\n" +
                "            \"copy_to\" : [\n" +
                "              \"fullText_suggest\"\n" +
                "            ],\n" +
                "            \"analyzer\" : \"ik_analyzer\"\n" +
                "          },\n" +
                "          \"enName_search\" : {\n" +
                "            \"type\" : \"text\",\n" +
                "            \"copy_to\" : [\n" +
                "              \"fullText_suggest\"\n" +
                "            ],\n" +
                "            \"analyzer\" : \"ik_analyzer\"\n" +
                "          },\n" +
                "          \"id\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"jpName_search\" : {\n" +
                "            \"type\" : \"text\",\n" +
                "            \"copy_to\" : [\n" +
                "              \"fullText_suggest\"\n" +
                "            ],\n" +
                "            \"analyzer\" : \"ik_analyzer\"\n" +
                "          },\n" +
                "          \"researchCode_search\" : {\n" +
                "            \"type\" : \"text\",\n" +
                "            \"copy_to\" : [\n" +
                "              \"fullText_suggest\"\n" +
                "            ],\n" +
                "            \"analyzer\" : \"ik_analyzer\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"expiryDate\" : {\n" +
                "        \"type\" : \"date\"\n" +
                "      },\n" +
                "      \"expiryDateObj\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"_aggs\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"expiryYear\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"familyCount\" : {\n" +
                "        \"type\" : \"integer\"\n" +
                "      },\n" +
                "      \"fullText\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"_suggest\" : {\n" +
                "            \"type\" : \"completion\",\n" +
                "            \"analyzer\" : \"standard\",\n" +
                "            \"preserve_separators\" : true,\n" +
                "            \"preserve_position_increments\" : true,\n" +
                "            \"max_input_length\" : 100\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"hasPdf\" : {\n" +
                "        \"type\" : \"integer\"\n" +
                "      },\n" +
                "      \"id\" : {\n" +
                "        \"type\" : \"keyword\",\n" +
                "        \"doc_values\" : false\n" +
                "      },\n" +
                "      \"ipcCode_search\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"isOrangeBook\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"_aggs\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"name\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"isTransfer\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"isTransfer_aggs\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"kindCode\" : {\n" +
                "        \"type\" : \"object\",\n" +
                "        \"enabled\" : false\n" +
                "      },\n" +
                "      \"legalStatusList\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"authrity\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"country\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"effectiveDate\" : {\n" +
                "            \"type\" : \"date\"\n" +
                "          },\n" +
                "          \"eventClass\" : {\n" +
                "            \"type\" : \"object\",\n" +
                "            \"enabled\" : false\n" +
                "          },\n" +
                "          \"eventClassDescription\" : {\n" +
                "            \"type\" : \"object\",\n" +
                "            \"enabled\" : false\n" +
                "          },\n" +
                "          \"eventCode\" : {\n" +
                "            \"type\" : \"object\",\n" +
                "            \"enabled\" : false\n" +
                "          },\n" +
                "          \"eventCodes\" : {\n" +
                "            \"properties\" : {\n" +
                "              \"eventCodeDesc\" : {\n" +
                "                \"type\" : \"object\",\n" +
                "                \"enabled\" : false\n" +
                "              },\n" +
                "              \"eventCodeExpl\" : {\n" +
                "                \"type\" : \"object\",\n" +
                "                \"enabled\" : false\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          \"eventDate\" : {\n" +
                "            \"type\" : \"date\"\n" +
                "          },\n" +
                "          \"id\" : {\n" +
                "            \"type\" : \"object\",\n" +
                "            \"enabled\" : false\n" +
                "          },\n" +
                "          \"patentId\" : {\n" +
                "            \"type\" : \"object\",\n" +
                "            \"enabled\" : false\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"normalNameYuan_search\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"normalName_search\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"otherPubNos\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"pubdate\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"pubid\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"pubno\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"priorCountryList\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"_aggs\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"name\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"priorDateList\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"priorDate\" : {\n" +
                "            \"type\" : \"date\"\n" +
                "          },\n" +
                "          \"priorNo_search\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"productType\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"productTypeList\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"_aggs\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"cnName\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"enName\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"id\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"pubId\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"pubDate\" : {\n" +
                "        \"type\" : \"date\"\n" +
                "      },\n" +
                "      \"pubId\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"pubNo_search\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"publicationList\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"abstr_search\" : {\n" +
                "            \"properties\" : {\n" +
                "              \"absContent\" : {\n" +
                "                \"type\" : \"text\",\n" +
                "                \"copy_to\" : [\n" +
                "                  \"fullText._suggest\"\n" +
                "                ]\n" +
                "              },\n" +
                "              \"absLanguage\" : {\n" +
                "                \"type\" : \"keyword\"\n" +
                "              },\n" +
                "              \"pubId\" : {\n" +
                "                \"type\" : \"keyword\"\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          \"applicantNameList\" : {\n" +
                "            \"properties\" : {\n" +
                "              \"_aggs\" : {\n" +
                "                \"type\" : \"keyword\"\n" +
                "              },\n" +
                "              \"applicantName_search\" : {\n" +
                "                \"type\" : \"keyword\"\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          \"applicantName_search\" : {\n" +
                "            \"type\" : \"text\",\n" +
                "            \"copy_to\" : [\n" +
                "              \"fullText._suggest\"\n" +
                "            ],\n" +
                "            \"analyzer\" : \"ik_analyzer\"\n" +
                "          },\n" +
                "          \"applyId\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"cpcList\" : {\n" +
                "            \"properties\" : {\n" +
                "              \"cpcCode_search\" : {\n" +
                "                \"type\" : \"text\",\n" +
                "                \"copy_to\" : [\n" +
                "                  \"fullText._suggest\"\n" +
                "                ],\n" +
                "                \"analyzer\" : \"ik_limit_analyzer\"\n" +
                "              },\n" +
                "              \"id\" : {\n" +
                "                \"type\" : \"object\",\n" +
                "                \"enabled\" : false\n" +
                "              },\n" +
                "              \"pubId\" : {\n" +
                "                \"type\" : \"keyword\"\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          \"expiryDate\" : {\n" +
                "            \"type\" : \"date\"\n" +
                "          },\n" +
                "          \"familyCount\" : {\n" +
                "            \"type\" : \"integer\"\n" +
                "          },\n" +
                "          \"familyIdList\" : {\n" +
                "            \"type\" : \"object\",\n" +
                "            \"enabled\" : false\n" +
                "          },\n" +
                "          \"hasPdf\" : {\n" +
                "            \"type\" : \"integer\"\n" +
                "          },\n" +
                "          \"id\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"inventorList\" : {\n" +
                "            \"type\" : \"text\",\n" +
                "            \"copy_to\" : [\n" +
                "              \"fullText._suggest\"\n" +
                "            ],\n" +
                "            \"analyzer\" : \"ik_analyzer\"\n" +
                "          },\n" +
                "          \"inventorYuanList\" : {\n" +
                "            \"type\" : \"text\",\n" +
                "            \"copy_to\" : [\n" +
                "              \"fullText._suggest\"\n" +
                "            ],\n" +
                "            \"analyzer\" : \"ik_analyzer\"\n" +
                "          },\n" +
                "          \"ipcList\" : {\n" +
                "            \"properties\" : {\n" +
                "              \"id\" : {\n" +
                "                \"type\" : \"object\",\n" +
                "                \"enabled\" : false\n" +
                "              },\n" +
                "              \"ipcCode_search\" : {\n" +
                "                \"type\" : \"text\",\n" +
                "                \"copy_to\" : [\n" +
                "                  \"fullText._suggest\"\n" +
                "                ],\n" +
                "                \"analyzer\" : \"ik_limit_analyzer\"\n" +
                "              },\n" +
                "              \"pubId\" : {\n" +
                "                \"type\" : \"keyword\"\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          \"kindCode\" : {\n" +
                "            \"type\" : \"object\",\n" +
                "            \"enabled\" : false\n" +
                "          },\n" +
                "          \"normalApplicantName_search\" : {\n" +
                "            \"type\" : \"text\",\n" +
                "            \"copy_to\" : [\n" +
                "              \"fullText._suggest\"\n" +
                "            ],\n" +
                "            \"analyzer\" : \"ik_analyzer\"\n" +
                "          },\n" +
                "          \"priorList\" : {\n" +
                "            \"properties\" : {\n" +
                "              \"_aggs\" : {\n" +
                "                \"type\" : \"keyword\"\n" +
                "              },\n" +
                "              \"country\" : {\n" +
                "                \"type\" : \"keyword\"\n" +
                "              },\n" +
                "              \"id\" : {\n" +
                "                \"type\" : \"object\",\n" +
                "                \"enabled\" : false\n" +
                "              },\n" +
                "              \"priorDate\" : {\n" +
                "                \"type\" : \"date\"\n" +
                "              },\n" +
                "              \"priorNo_search\" : {\n" +
                "                \"type\" : \"text\",\n" +
                "                \"copy_to\" : [\n" +
                "                  \"fullText._suggest\"\n" +
                "                ],\n" +
                "                \"analyzer\" : \"ik_limit_analyzer\"\n" +
                "              },\n" +
                "              \"priorYear\" : {\n" +
                "                \"type\" : \"keyword\"\n" +
                "              },\n" +
                "              \"pubId\" : {\n" +
                "                \"type\" : \"keyword\"\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          \"pubDate\" : {\n" +
                "            \"type\" : \"date\"\n" +
                "          },\n" +
                "          \"pubNoAbbr\" : {\n" +
                "            \"type\" : \"object\",\n" +
                "            \"enabled\" : false\n" +
                "          },\n" +
                "          \"pubNo_search\" : {\n" +
                "            \"type\" : \"text\",\n" +
                "            \"copy_to\" : [\n" +
                "              \"fullText._suggest\"\n" +
                "            ],\n" +
                "            \"analyzer\" : \"ik_limit_analyzer\"\n" +
                "          },\n" +
                "          \"sortTitle\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"title_search\" : {\n" +
                "            \"properties\" : {\n" +
                "              \"pubId\" : {\n" +
                "                \"type\" : \"keyword\"\n" +
                "              },\n" +
                "              \"tiContent\" : {\n" +
                "                \"type\" : \"text\",\n" +
                "                \"copy_to\" : [\n" +
                "                  \"fullText._suggest\"\n" +
                "                ]\n" +
                "              },\n" +
                "              \"tiLanguage\" : {\n" +
                "                \"type\" : \"keyword\"\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          \"updateDate\" : {\n" +
                "            \"type\" : \"date\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"realStatus\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"realStatus_aggs\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"sortPriorDate\" : {\n" +
                "        \"type\" : \"date\"\n" +
                "      },\n" +
                "      \"sortPubDate\" : {\n" +
                "        \"type\" : \"date\"\n" +
                "      },\n" +
                "      \"sortTitle\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"target\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"targetList\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"_aggs\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"cnAliasName\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"cnName\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"enAliasName\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"enName\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"id\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"pubId\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"targetTreeAggs\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"targetTreeBizId\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"techClass\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"techClassList\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"_aggs\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"name\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"pubId\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"therapyArea\" : {\n" +
                "        \"type\" : \"keyword\"\n" +
                "      },\n" +
                "      \"therapyAreaList\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"_aggs\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"cnName\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"enName\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"id\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"pubId\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"title_search\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"pubId\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"tiContent\" : {\n" +
                "            \"type\" : \"text\"\n" +
                "          },\n" +
                "          \"tiLanguage\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"updateDate\" : {\n" +
                "        \"type\" : \"date\"\n" +
                "      },\n" +
                "      \"wholePublicationList\" : {\n" +
                "        \"properties\" : {\n" +
                "          \"_aggs\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"id\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"pubDate\" : {\n" +
                "            \"type\" : \"date\"\n" +
                "          },\n" +
                "          \"pubNo_search\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          },\n" +
                "          \"pubYear\" : {\n" +
                "            \"type\" : \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

    }
}
