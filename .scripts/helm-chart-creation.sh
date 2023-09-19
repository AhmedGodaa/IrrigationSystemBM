# create helm chart
helm create irrigation-chart
# delete default templates
rm -r kubernetes/irrigation-chart/templates/*
# generate yaml files from templates
helm template kubernetes/helm-test-chart  > kubernetes/generated/helm-generated-chart.yaml

