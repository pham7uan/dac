/** 
 * Copyright 2017 Telerik AD                                                                                                                                                                            
 *                                                                                                                                                                                                      
 * Licensed under the Apache License, Version 2.0 (the "License");                                                                                                                                      
 * you may not use this file except in compliance with the License.                                                                                                                                     
 * You may obtain a copy of the License at                                                                                                                                                              
 *                                                                                                                                                                                                      
 *     http://www.apache.org/licenses/LICENSE-2.0                                                                                                                                                       
 *                                                                                                                                                                                                      
 * Unless required by applicable law or agreed to in writing, software                                                                                                                                  
 * distributed under the License is distributed on an "AS IS" BASIS,                                                                                                                                    
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.                                                                                                                             
 * See the License for the specific language governing permissions and                                                                                                                                  
 * limitations under the License.                                                                                                                                                                       
                                                                                                                                                                                                       
                                                                                                                                                                                                       
                                                                                                                                                                                                       
                                                                                                                                                                                                       
                                                                                                                                                                                                       
                                                                                                                                                                                                       
                                                                                                                                                                                                       
                                                                                                                                                                                                       

*/

(function(f){
    if (typeof define === 'function' && define.amd) {
        define(["kendo.core"], f);
    } else {
        f();
    }
}(function(){
(function( window, undefined ) {
    kendo.cultures["jgo"] = {
        name: "jgo",
        numberFormat: {
            pattern: ["-n"],
            decimals: 2,
            ",": ".",
            ".": ",",
            groupSize: [3],
            percent: {
                pattern: ["-n%","n%"],
                decimals: 2,
                ",": ".",
                ".": ",",
                groupSize: [3],
                symbol: "%"
            },
            currency: {
                name: "",
                abbr: "",
                pattern: ["-$ n","$ n"],
                decimals: 0,
                ",": ".",
                ".": ",",
                groupSize: [3],
                symbol: "FCFA"
            }
        },
        calendars: {
            standard: {
                days: {
                    names: ["S????ndi","M????ndi","??pta M????ndi","W????n??s??d??","T????s??d??","F??l??y??d??","S??sid??"],
                    namesAbbr: ["S????ndi","M????ndi","??pta M????ndi","W????n??s??d??","T????s??d??","F??l??y??d??","S??sid??"],
                    namesShort: ["S????ndi","M????ndi","??pta M????ndi","W????n??s??d??","T????s??d??","F??l??y??d??","S??sid??"]
                },
                months: {
                    names: ["Ndu??mbi Sa??","P??sa?? P????p??","P??sa?? P????t??t","P??sa?? P????n????kwa","P??sa?? Pataa","P??sa?? P????n????nt??k??","P??sa?? Saamb??","P??sa?? P????n????f??m","P??sa?? P????n????pf???????","P??sa?? N??g????m","P??sa?? Nts????pm????","P??sa?? Nts????pp??"],
                    namesAbbr: ["Ndu??mbi Sa??","P??sa?? P????p??","P??sa?? P????t??t","P??sa?? P????n????kwa","P??sa?? Pataa","P??sa?? P????n????nt??k??","P??sa?? Saamb??","P??sa?? P????n????f??m","P??sa?? P????n????pf???????","P??sa?? N??g????m","P??sa?? Nts????pm????","P??sa?? Nts????pp??"]
                },
                AM: ["AM","am","AM"],
                PM: ["PM","pm","PM"],
                patterns: {
                    d: "yyyy-MM-dd",
                    D: "dddd, yyyy MMMM dd",
                    F: "dddd, yyyy MMMM dd HH:mm:ss",
                    g: "yyyy-MM-dd HH:mm",
                    G: "yyyy-MM-dd HH:mm:ss",
                    m: "MMMM d",
                    M: "MMMM d",
                    s: "yyyy'-'MM'-'dd'T'HH':'mm':'ss",
                    t: "HH:mm",
                    T: "HH:mm:ss",
                    u: "yyyy'-'MM'-'dd HH':'mm':'ss'Z'",
                    y: "yyyy MMMM",
                    Y: "yyyy MMMM"
                },
                "/": "-",
                ":": ":",
                firstDay: 1
            }
        }
    }
})(this);
}));