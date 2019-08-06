const template = ['access', 'behavior', 'impacts', 'limitations', 'enhancements', 'troubleshooting'];
    

const articles = {
    'affiliation update': {
        id: 'affiliation update',
        title: 'update an affiliation',
        summary: 'how to update a right owner affiliation, what will be impacted?',
        access: {
            content: "going to documentation&gt;right owners, and accessing the detail page of a right "+
            "owner. In the main page of the right owner detail, section \"AFFILIATION\"<br/>",
            image: 'img/affiliation_update-access.png'
        },
        behavior: {
            content: "Affiliations can be updated in WIPO Connect <i>local</i>, using the "
            +"detail screen of the right owners. Scrolling down on the main tab, the "
            +"affiliation section can be expended, and the operator has the option to:"
            +"<ul><li>Remove an existing affiliation line (clicking on the - button of the "
            +"relevant line)</li> <li>Add a new affiliation line (clicking on the + button "
            +"at the right top corner of the table. When adding a new affiliation, the "
            +"operator should select the role(s) for which the affiliation will be added, "
            +"then specify the territory of the affiliation (in the territory box at the "
            +"right top corner of the screen), and the CMO of affiliation. By default, an "
            +"operator can only add affiliation lines for the current CMO. However, it is "
            +"possible to add affiliation to all the CMOs for which the agreement "
            +"repertoire (in the reference&gt;societies screen"
            +"screen) is set to the creation class of the affiliation.</li></ul>"
        },
        impacts: {
           content:"affiliation update has an impact on:<ul><li>distribution</li><li>works</li></ul>"
        },
        limitations : {
            content:"modifying affiliations through the user interface is limited: "
            +"the operator cannot specify the rights of the affiliation (it covers all "
            +"the right types), and cannot specify the dates of the the affiliation (the "
            +"start date is the date when the affiliation is added). If more detailed "
            +"affiliation updates are needed, it can be done by exporting the right owner "
            +"(select the right owner in the search result grid - check box on the left of "
            +"the line, and click on the download massive action), modifying the data "
            +"within the exported file (please refer to the user manual to see the format) "
            +"setting the transaction to UPDATE and re-importing the file<br/>"
        },
        enhancements : {
            content:"a new feature request has been registered (id= 17) to allow a full "
            +"flexibility on the management of affiliations from the user interface.<br/>"
        },
        troubleshooting : {
            content:"<b>issue 1</b> - \"an error has occurred\": the configuration fo the "
            +"date format creates an issue with the generation of the affiliation date, it "
            +"is not possible to add a new affiliation. <br/>solution - contact WIPO team "
            +"so that the error can be analyzed deeply<br/><br/>"
            +"<b>issue 2</b> - \"affiliation already present have not been added to the RO\": the "
            +"operator tries to add an affiliation that already exists (that conflicts with "
            +"another affiliation). The solution prevents the creation of conflicting "
            +"affiliations<br/>solution - the operator has to review the affiliation before "
            +"trying to add additional lines<br/>"
        }
    },
    'suspend': {
        id : 'suspend',
        title: 'suspend a work',
        summary: 'how to suspend and un-suspend a work, '
            +'what will be impacted?',
        access: {
            content:"going to documentation&gt;search&gt;works, accessing the detail page of a work. "
            +"at the right bottom of the page, clicking on the arrow next to \"save\", "
            +"and selecting \"suspend\"<br/>",
            image: 'img/work_suspend-access.png'
        },
        behavior: {
            content: "A suspended work is a work that can be used as a covered work of a revenue "
            +"stream, but for which the allocated amount will not be distributed and put in the "
            +"undistributable amount. Depending on the parameter of the solution, the amount can "
            +"be distributed to distributable right owners, or kept in a suspended account."
            +"<br/>Suspended works appear in the documentation>pending>works screen.<br/>"
        },
        impacts: {
            content:"suspend has an impact on:<ul><li>distribution</li><li>list of pending works</li></ul>"
        },
        limitations : { 
            content:"The amount allocated to suspended works cannot be reserved for these "
            +"specific works. However, there is a way of creating, for a future distribution, "
            +"work list based streams that can be used to do so. It requires some manual "
            +"tasks.<br/>"
        },
        enhancements : {
            content: "No request has been attached to this feature.<br/>"
        },
        troubleshooting : {
            content:"No known issue.<br/>"
        }
    },
    'to be corrected': {
           id: 'to be corrected',
        title: 'flag a work to be corrected',
        summary: 'flag and un-flag a work "to be corrected", '
            +'what will be impacted?',
        access: {
            content:"going to documentation&gt;search&gt;works, accessing the detail page of a work. "
            +"at the top of the main tab, switching on or off the \"to be corrected\" switch<br/>",
            image: 'img/work_to_be_corrected-access.png'
        },
        behavior: {
            content: "A \"to be corrected\" work is a work that has been registered, but for which additional "
            +" action should be undertaken. For example, this switch can be used to identify works that have "
            +"been registered in the system, but not declared or confirmed by the right owners."
            +"These works can be used in revenue streams and will be allocated and distributed. The flag "
            +"is na information flag. Such works can be found in the documentation>pending>works screen.<br/>"
        },
        impacts: {
            content:"to be corrected flag has an impact on:<ul><li>list of pending works</li></ul>"
        },
        limitations : { 
            content:"The \"to be corrected\" works cannot be looked for from the work search screen. "
            +"It is not considered as a limitation, since the works can be found from the pending work screen.<br/>"
        },
        enhancements : {
            content: "No request has been attached to this feature.<br/>"
        },
        troubleshooting : {
            content:"No known issue.<br/>"
        }
    },
    'initial configuration: creation classes': {
           id: 'initial configuration: creation classes',
        title: 'initial configuration: creation classes',
        summary: 'configure the managed creation classes, '
            +'where and how?',
        access: {
            content:"reference > codes, creation class section: select the creation class managed by the cmo.<br/><br/>",
            image: 'img/creation_class-configuration.png'
        },
        behavior: {
            content: "The list of creation classes is used to select the creation classes managed by the CMO. The button \"add creation class\" is used to add a managed creation class to the current list.<br/><br/>"
            +"The following parameters need to be set:<ul>"
            +"<li>share base: this is the base of the shares of a work of the creation class. If the share are documented in %, the share base shall be 100. If the shares are given in points or weights, the field shall be left empty.</li>"
            +"<li>work identifier: this is the prefered identifier for works of this creation class. For musical works (MW), the common value is ISWC. For sound recordings (SR), the value would be ISRC.</li>"
            +"<li>additional fields: this is the list of additional information managed for works of the creation class. For example, \"perfomer\" is a common additional field for MW, while \"support\" or \"label\" are common additional fields for SR.</li>"
            +"<li>domestic work: this is the list of roles that will be considered to calculate if a work is domestic or not. For example, for MW, common roles will be A (author), C (composer), E (music publisher).</li>"
            +"</ul>The configration of the creation class is mandatory: the solution cannot work without."
        },
        impacts: {
            content:"Creation class configuration has an impact on:<ul><li>work documentation</li><li>agreement management</li><li>licensing</li><li>matching</li><li>distribution</li></ul>"
        },
        limitations : { 
            content:"The update of this parameter will cause the repertoire information of works to be outdated. The solution will recalculate the repertoire of each works, which can take time, depending on the number of works in the database."
        },
        enhancements : {
            content: "No request has been attached to this feature.<br/>"
        },
        troubleshooting : {
            content:"No known issue.<br/>"
        }
    },
    'initial configuration: territory': {
           id: 'initial configuration: territory',
        title: 'initial configuration: territory',
        summary: 'configure the operation territories, '
            +'where and how?',
        access: {
            content:"administration > business parameters, territoy sections: set the various territories.<br/><br/>",
            image: 'img/territories-configuration.png'
        },
        behavior: {
            content: "The territory configuration defines the geographical parameter of the CMO's operations.<br/><br/>"
            +"The following territory can be set:<ul>"
            +"<li>default territories of affiliation: these territories are used when a right owner is registered. The affiliation of the right owner will be defined for this set of territories.</li>"
            +"<li>default work territories: these territories are used when a work is registered. The ownership view of the work will be valide for this set of territories.</li>"
            +"<li>country of operations: this territory is used by the solution when allocating royalties to the works, during the distribution process.</li>"
            +"</ul>The configration of the country of operations is mandatory: the solution cannot work without."
        },
        impacts: {
            content:"Territory configuration has an impact on:<ul><li>right owner registration</li><li>work registration</li><li>distribution</li></ul>"
        },
        limitations : { 
            content:"Only one territory can be set as a country of operation. When a CMO manages several territories, a specific instance of WIPO Connect local should be deployed for each operation country."
        },
        enhancements : {
            content: "No request has been attached to this feature.<br/>"
        },
        troubleshooting : {
            content:"No known issue.<br/>"
        }
    },
    'initial configuration: right split': {
           id: 'initial configuration: right split',
        title: 'initial configuration: right split',
        summary: 'configure the right type splits, '
            +'where and how?',
        access: {
            content:"references > codes, right split section: configure the link between right types and right categories.<br/><br/>",
            image: 'img/split-configuration.png'
        },
        behavior: {
            content: "For historical reasons, the rights documented for right owner affiliations are not the rights documented for works. For right owner affiliations, WIPO Connect manages \"right types\" (sometime refered as \"usage types\"), while for work documentation, WIPO Connect manages \"right categories\"."
            +"When doing a distribution, WIPO Connect uses a link between the right types (the revenue streams are per right type) and the right categories, so that the correct work shares can be used.<br/><br/>"
            +"For example, when doing a allocation of a revenue coming from a Digital Service Provider that allows download, the right type of the revenue stream will be \"OD\", the making available right, interactive. Some CMOs consider that this right should be distributed "
            +"66% as a mechanical right category (MEC) amd 34% as a performing right category (PER).<br/><br/>"
            +"For each of the right type managed by the CMO, the splits have to be defined for each right categories relevant to a given creation class."
            +"In the right split section, all the right types for which the splits have to be defined will be displayed in red (in the screenshot, DB and ER right types should be configurated). <br/><br/>"
            +"Note that a CMO shall only define the right splits for the right types it licenses.<br/><br/>"
        },
        impacts: {
            content:"Right split configuration has an impact on:<ul><li>work allocation</li><li>right owner allocation</li><li>revenue stream management</li></ul>"
        },
        limitations : { 
            content:"No limitation."
        },
        enhancements : {
            content: "The list of right split should be limited to the managed creation classes. Currently all the creation classes should be set-up.<br/>"
        },
        troubleshooting : {
            content:"No known issue.<br/>"
        }
    }

}

