#!/bin/bash
echo "From: vtnetwelt.notfications@gmail.com" > /tmp/gitlab-statusreport$CI_PIPELINE_IID.txt
echo "To: kuldeep.chander@vtnetzwelt.com" >> /tmp/gitlab-statusreport$CI_PIPELINE_IID.txt
echo "Cc: pratik.chauhan@vtnetzwelt.com" >> /tmp/gitlab-statusreport$CI_PIPELINE_IID.txt
echo "Cc: deepti.jindal@vtnetzwelt.com" >> /tmp/gitlab-statusreport$CI_PIPELINE_IID.txt
echo "Subject: Status of last executed test case $CI_JOB_NAME on $CI_PROJECT_NAME">> /tmp/gitlab-statusreport$CI_PIPELINE_IID.txt
echo "Plese refer to the url below  to find  the Complete status report" >>  /tmp/gitlab-statusreport$CI_PIPELINE_IID.txt
# echo "" >>  /tmp/gitlab-statusreport$CI_JOB_ID.txt
# echo "" >>  /tmp/gitlab-statusreport$CI_JOB_ID.txt
# echo "" >>  /tmp/gitlab-statusreport$CI_JOB_ID.txt
# echo "Status of job $CI_JOB_STATUS on branch $CI_COMMIT_REF_NAME" >>  /tmp/gitlab-statusreport$CI_PIPELINE_IID.txt
# echo "OR"  >>  /tmp/gitlab-statusreport$CI_PIPELINE_IID.txt
echo "Status of job on branch $CI_COMMIT_REF_NAME - http://gitlab.vtnetzwelt.com/deepti.jindal/browserstack_myscan_qa_ios/-/jobs/$CI_JOB_ID" >>  /tmp/gitlab-statusreport$CI_PIPELINE_IID.txt
curl --ssl-reqd --url 'smtps://smtp.gmail.com:465' --user 'vtdevops@gmail.com:$smtp_password' --mail-from 'vtdevops@gmail.com' --mail-rcpt 'kuldeep.chander@vtnetzwelt.com' --mail-rcpt 'pratik.chauhan@vtnetzwelt.com' --mail-rcpt 'deepti.jindal@vtnetzwelt.com' --upload-file /tmp/gitlab-statusreport$CI_PIPELINE_IID.txt
rm -rf /tmp/gitlab-statusreport$CI_PIPELINE_IID.txt
